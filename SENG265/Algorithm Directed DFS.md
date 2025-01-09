Algorithm Directed DFS

Algorithm DirectedDFS(𝐺, 𝑣):
    Input: A digraph G and a vertex v of G
    Output: A label of the edges as discovery, back, forward or cross edges

    Label 𝑣 as active
    for each outgoing edge 𝑒 do
        if 𝑒 is unexplored then
            Let 𝑤 be the destination of 𝑒
            if w is unexplored and not active then
                Label e as a discovery edge
                DirectedDFS(𝐺,w)
            else if 𝑤 is active then
                mark edge 𝑒 as a back edge
            else if w is explored and w's preorder is greater than v's order
                mark edge 𝑒 as a forward edge
            else 
                mark edge e as a cross edge
    Label 𝑣 as explored 