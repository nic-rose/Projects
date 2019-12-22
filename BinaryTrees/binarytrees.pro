%%%%%%%%%%%%%%%%%%%%%%%%%%
% tree.pl
% https://gfx.cse.taylor.edu/courses/cos382/assignments/04_ParadigmLogic_Prolog
% The goal of this assignment is to write a collection of Prolog rules
% to represent and manipulate binary trees.
%%%%%%%%%%%%%%%%%%%%%%%%%%

%%%%%%%%%%%%%%%%%%%%%%%%%%
% Starter code

% tree(Tree)
% "Tree" is a binary tree.

tree(void).
tree(tree(_,Left,Right)) :-  tree(Left),
                             tree(Right).

% tree_member(Element,Tree)
% "Element" is an element of the binary tree "Tree".

tree_member(X,tree(X,_,_)).
tree_member(X,tree(_,Left,_)) :- tree_member(X,Left).
tree_member(X,tree(_,_,Right)) :- tree_member(X,Right).

% preorder(Tree,Pre)
% "Pre" is a list of elements of "Tree" accumulated during a
% preorder traversal.

preorder(tree(X,L,R),Xs) :- preorder(L,Ls), preorder(R,Rs),
                            append([X|Ls],Rs,Xs).
preorder(void,[]).

% append(Xs,Ys,XsYs)
% "XsYs" is the result of appending the lists "Xs" and "Ys".

append([],Ys,Ys).
append([X|Xs],Ys,[X|Zs]) :- append(Xs,Ys,Zs).

% Some sample trees
%
%    tree1       tree2         tree3
%
%      1           4             1
%     / \         / \           / \
%    2   3       5   6         2   3
%                             / \
%                            5   6
%                               /
%                              7
%

tree1(tree(1,tree(2,void,void),tree(3,void,void))).
tree2(tree(4,tree(5,void,void),tree(6,void,void))).
tree3(
        tree(   1,
                tree(   2,
                        tree(5,void,void),
                        tree(   6,
                                tree(7,void,void),
                                void
                        )
                ),
                tree(3,void,void)
        )
).

%%%%%%%%%%%%%%%%%%%%%%%%%%
% Place your code here

% Additional test data

% inorder
inorder(void, List) :- List=[].
inorder(tree(H,L,R), List) :- inorder(L,LL), inorder(R,RR), append(LL,[H|RR],List).

% search
search(tree(Key,L,R), Key).
search(tree(H,L,R), Key) :- search(L,Key); search(R,Key).

% binarySearch
binsearch(BSTOfIntegers, Key)

% subtree
subtree(T,T).
% subtree(S,[H|Tail]) :- subtree(Tail, T).
subtree(S, tree(H,L,R)) :- subtree(S, L); subtree(S, R).

% sumtree
sumtree(void,0).
sumtree(tree(H,L,R), Sum) :- sumtree(L, X), sumtree(R, Y), Sum is X + Y + H.

% ordered
accend([]).
accend([_]).
accend([H1,H2|T]) :- H1 < H2, accend([H2|T]).
ordered(Tree) :- inorder(Tree, List), accend(List).

% substitute
substitute(X,Y,void,void).
substitute(X,Y,tree(H,L,R),tree(H2,L2,R2)) :- H = X, H2 = Y, substitute(X,Y,L,L2), substitute(X,Y,R,R2).
substitute(X,Y,tree(H,L,R),tree(H2,L2,R2)) :- \+(H==X), H=H2, substitute(X,Y,L,L2), substitute(X,Y,R,R2). 

% pretty print
prettyprint(Tree).