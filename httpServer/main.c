#include <stdio.h>
#include <netdb.h>
#include <string.h>
#include <arpa/inet.h>
#include <errno.h>
#include <unistd.h>
#include <stdlib.h>
#include <fcntl.h>
#include <time.h>

#define MYHOST "localhost"
//#define MYPORT "8080"
#define BACKLOG 10


void response(int acceptfd, char* path){
    char buffer[32];
    time_t currentTime = time(0);
    struct tm tm = *localtime(&currentTime);
    strftime(buffer, sizeof buffer, "%a, %d %b %Y %H:%M:%S %Z", &tm);
    dprintf(acceptfd,"Date: ");
    write(acceptfd, buffer, 32);

    dprintf(acceptfd, "\nContent-Type: ");
    char* type = strrchr(path,'.');

    if(type == NULL){
        dprintf(acceptfd, "Unknown Type\n");
    }
    else if(strcmp(type,".jpg") == 0){
        dprintf(acceptfd, "image/jpeg\n");
    }
    else if (strcmp(type,".png") == 0) {
        dprintf(acceptfd, "image/png\n");
    }
    else if (strcmp(type,".gif") == 0) {
        dprintf(acceptfd, "image/gif\n");
    }
    else if (strcmp(type,".pdf") == 0) {
        dprintf(acceptfd, "application/pdf\n");
    }
    else if (strcmp(type,".js") == 0) {
        dprintf(acceptfd, "application/javascript\n");
    }
    else if (strcmp(type,".html") == 0) {
        dprintf(acceptfd, "text/html\n");
    }
    else if (strcmp(type,".txt") == 0) {
        dprintf(acceptfd, "text/txt\n");
    }
    else if (strcmp(type,".css") == 0) {
        dprintf(acceptfd, "text/css\n");
    }
}

int handle_connection(int acceptfd) {
    char input_buffer[4096];
//    printf("third\n");
    long numBytes = recv(acceptfd, &input_buffer, 4096, 0);
    printf("%d\n", (int)numBytes);

    char* x = strtok(input_buffer,"\n");
    char* method = strtok(x," ");
//    printf("%s\n", method);
    char* path = strtok(NULL," ");
    for(int i=0; i < (int) strlen(path); i++){
        path[i] = path[i+1];
    }
//    printf("%s\n", path);
    char* version = strtok(NULL," ");

    if(strncmp("GET", method, 3) == 0 || (strncmp("HEAD", method, 4) == 0)){
        if(strncmp("HTTP/1.1",version, 8) == 0 || strncmp("HTTP/1.0",version, 8) == 0) {
            int fd = open(path, O_RDONLY, 0);
            if (fd == -1) {
                dprintf(acceptfd, "HTTP/1.0 404 Not Found\n");
                response(acceptfd, path);
//                dprintf(acceptfd, "Content-Length: ");
//                dprintf(acceptfd, "24\n");
            }
            else{
                //file exists
                //response header
                dprintf(acceptfd, "HTTP/1.0 200 OK\n");
                response(acceptfd, path);
                dprintf(acceptfd, "Content-Length: ");
                off_t length = lseek(fd, 0, SEEK_END);
                dprintf(acceptfd, "%d\n", (int)length);
                dprintf(acceptfd, "\r\n");
                //response body if the method is Get, Head does not need it
                if(strncmp("HEAD", method, 4) != 0) {

                    char out_buffer[length + 1];
                    lseek(fd, 0, 0);
                    int readin;
                    while((readin = read(fd, out_buffer, (size_t) length + 1))>0){
                        write(acceptfd,out_buffer,readin);
                    }
                    send(fd, out_buffer, (size_t) length+1, 0);
                }
            }
        }
        else{
            dprintf(acceptfd, "HTTP/1.0 400 Bad Request\n");
            response(acceptfd, path);
//            dprintf(acceptfd, "Content-Length: ");
//            dprintf(acceptfd, "25\n");
        }

    }
    else{

//        printf("Bad request");
        return 0;
    }
    return 1;
}

int main(int argv, char** argc) {
//    char* host = argc[2];
    printf("%d", argv);
    char* port = argc[3];


    struct addrinfo hints;
    struct addrinfo * address_resource;
    int socket_desc;

    memset(&hints, 0, sizeof hints);
    hints.ai_family = AF_INET;       // IPv4
    hints.ai_socktype = SOCK_STREAM; // Streaming Protocol (sets resulting ai_protocol to TCP on most systems)
    hints.ai_flags = AI_PASSIVE;     // Listen

    getaddrinfo(MYHOST, port, &hints, &address_resource);
    socket_desc = socket(
            address_resource->ai_family,     // IPv4
            address_resource->ai_socktype,   // Streaming Protocol
            address_resource->ai_protocol);  // TCP

    int enable = 1;
    if (setsockopt(socket_desc, SOL_SOCKET, SO_REUSEADDR, &enable, sizeof(int))){
        printf("Error: setsockopt failed!\n");
        return 1;
    }

    int tmp_ret = bind(socket_desc, address_resource->ai_addr, address_resource->ai_addrlen);
    if (tmp_ret != 0) {
        printf("Error: %s\n", strerror(errno));
        return 1;
    }

    freeaddrinfo(address_resource);
    listen(socket_desc, BACKLOG);
    int accept_desc;

    struct sockaddr_storage remote_addr;
    socklen_t remote_addr_s = sizeof remote_addr;
    while(1) {
        accept_desc = accept(socket_desc, (struct sockaddr *) &remote_addr, &remote_addr_s);

        if (!fork()) {
            // child process
            if(handle_connection(accept_desc)) {
//                printf("%s\n", strerror(errno));

                close(accept_desc);
                exit(0);
            }
        }
        // parent process
        close(accept_desc);

    }

    return 0;
}

