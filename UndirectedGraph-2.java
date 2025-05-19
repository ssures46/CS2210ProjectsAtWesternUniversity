//Author Name: Sivaramchandar Suresh
//File Name: UndirectedGraph.java
//Date: April 7, 2025.
/*
The purpose of this file is to 
*/
import java.util.ArrayList;
import java.util.Iterator;
public class UndirectedGraph implements GraphADT{
    private GraphNodes<GraphEdge> [] nodes;
    private GraphNode []graphNodes;
    
    public UndirectedGraph(int n)
    {
        //GraphNode[]nodes= new GraphNode[n];
        nodes= new GraphNodes[n];
        graphNodes= new GraphNode[n];
        for(int i= 0; i<n; i++)
        {
            graphNodes[i]= new GraphNode(i);
           // nodes[i]= new GraphNodes<GraphEdge>();
            //nodes[i].setData(new GraphEdge(new GraphNode(i), new GraphNode(i), 'c'));
            //nodes[i]= new GraphEdge(new GraphNode(i), new GraphNode(i), ' '));
        }
        
    }
    /*
    This method inserts a edge connecting the two nodes u and v. This method 
    throws a GraphException if there already exist a edge connecting u and v.
    If either u or v don't exist in the graph, this method throws a 
    GraphException.
    */
    public void insertEdge(GraphNode u, GraphNode v, char edgeType) throws GraphException
    {
        GraphNodes<GraphEdge> []currentNode= nodes;
        GraphNodes<GraphEdge> nextNode= null;
        if (checkGraphNodeExistInTheGraph(u)==false || checkGraphNodeExistInTheGraph(v)==false)
        {
            throw new GraphException(u + " or " + v + " does not exist in the graph");
        }
        if (currentNode!=null && currentNode[u.getName()]==null)
        {
            currentNode[u.getName()]= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType));
        }
        while(currentNode!= null && currentNode[u.getName()]!=null && currentNode[u.getName()].getData()!= null)
        {
            if (currentNode[u.getName()].getData().firstEndpoint()!= null && currentNode[u.getName()].getData().secondEndpoint()!= null)
            {
                if (currentNode[u.getName()].getData().firstEndpoint().getName()==u.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==v.getName())
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
                if (currentNode[u.getName()].getData().firstEndpoint().getName()==v.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==u.getName())
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
            }
            if (currentNode[u.getName()].getNext()==null)
            {
                nextNode= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType));
                currentNode[u.getName()].setNext(nextNode);
                nextNode.setNext(null);
                break;
            }
            currentNode[u.getName()]= currentNode[u.getName()].getNext();
        }
        while(currentNode!= null && currentNode[v.getName()]!=null && currentNode[v.getName()].getData()!= null)
        {
            if (currentNode[v.getName()].getData().firstEndpoint()!= null && currentNode[v.getName()].getData().secondEndpoint()!= null)
            {
                if (currentNode[v.getName()].getData().firstEndpoint().getName()==u.getName() && currentNode[v.getName()].getData().secondEndpoint().getName()==v.getName())
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
                if (currentNode[v.getName()].getData().firstEndpoint().getName()==v.getName() && currentNode[v.getName()].getData().secondEndpoint().getName()==u.getName())
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
            }
            if (currentNode[v.getName()].getNext()==null)
            {
                nextNode= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType));
                currentNode[v.getName()].setNext(nextNode);
                nextNode.setNext(null);
                break;
            }
            currentNode[v.getName()]= currentNode[v.getName()].getNext();
        }
        /*if (currentNode[v.getName()]!= null && currentNode[v.getName()].getData()!= null && areEdgesTheSame(currentNode[v.getName()].getData(), getEdge(v, v)))
        {
            currentNode[v.getName()].setData(new GraphEdge(u, v, edgeType));
        }
        else
        {
            while(currentNode[v.getName()]!=null && currentNode[v.getName()].getData()!= null)
            {
                if (currentNode[v.getName()].getData().firstEndpoint()==u && currentNode[v.getName()].getData().secondEndpoint()==v)
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
                if (currentNode[v.getName()].getData().firstEndpoint()==v && currentNode[v.getName()].getData().secondEndpoint()==u)
                {
                    throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                }
                currentNode[v.getName()]= currentNode[v.getName()].getNext();
            }
            currentNode[v.getName()]= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType));
        }*/
        
    }
    /*
    This method inserts a new edge connectin the two graph nodes u and v. This 
    method throws a GraphException if there is already an edge connecting u and 
    v. This method also throws a GraphException if either u or v don't exist in
    the GraphException.
    */

    public void insertEdge(GraphNode u, GraphNode v, char edgeType, String label) throws GraphException
    {
        GraphNodes<GraphEdge> []currentNode= nodes;
        GraphNodes<GraphEdge> nextNode= null;
        //GraphNodes<GraphEdge> previousNode= null;
        if (checkGraphNodeExistInTheGraph(u)==false || checkGraphNodeExistInTheGraph(v)==false)
        {
            throw new GraphException(u + " or " + v + " does not exist in the graph");
        }
        if(currentNode!= null && currentNode[u.getName()]== null)
        {
            currentNode[u.getName()]= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType, label));
            currentNode[u.getName()].setNext(null);
        }
        else
        {
            while(currentNode!=null && currentNode[u.getName()]!=null && currentNode[u.getName()].getData()!= null)
            {
                if (currentNode[u.getName()].getData().firstEndpoint()!=null && currentNode[u.getName()].getData().secondEndpoint()!=null)
                {
                    if (currentNode[u.getName()].getData().firstEndpoint().getName()==u.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==v.getName())
                    { 
                        throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                    }
                    if (currentNode[u.getName()].getData().firstEndpoint().getName()==v.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==u.getName())
                    {
                        throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                    }
                }
                if (currentNode[u.getName()].getNext()==null)
                {
                    nextNode= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType, label));
                    currentNode[u.getName()].setNext(nextNode);
                    break;
                }
                currentNode[u.getName()]= currentNode[u.getName()].getNext();
            }
        }
        if (currentNode!=null && currentNode[v.getName()]==null)
        {
            currentNode[v.getName()]= new GraphNodes<GraphEdge>(new GraphEdge(u,v, edgeType, label));
        }
        else
        {
            while(currentNode!= null && currentNode[v.getName()]!=null && currentNode[v.getName()].getData()!= null)
            {
                if (currentNode[v.getName()].getData().firstEndpoint()!= null && currentNode[u.getName()].getData().secondEndpoint()!=null)
                {
                    if(currentNode[v.getName()].getData().firstEndpoint().getName()== u.getName() && currentNode[v.getName()].getData().secondEndpoint().getName()==v.getName())
                    {
                        throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                    }
                    else if (currentNode[v.getName()].getData().firstEndpoint().getName()== v.getName() && currentNode[v.getName()].getData().secondEndpoint().getName()==u.getName())
                    {
                        throw new GraphException("There already exists a edge between " + u + " and " + v + " in the graph");
                    }
                }
                if (currentNode[v.getName()].getNext()==null)
                {
                    nextNode= new GraphNodes<GraphEdge>(new GraphEdge(u, v, edgeType, label));
                    currentNode[v.getName()].setNext(nextNode);
                    break;
                }
                currentNode[v.getName()]= currentNode[v.getName()].getNext();
            }
        }
        /*if (currentNode[v.getName()]!= null && currentNode[v.getName()].getData()== getEdge(getNode(v.getName()), getNode(v.getName())))
        {
            currentNode[u.getName()].setData(new GraphEdge(u, v, edgeType));
            currentNode[v.getName()].setData(new GraphEdge(u, v, edgeType));
        }*/
        
    }
    /*
    This method returns a node in the graph with name. 
    */
    public GraphNode getNode(int name) throws GraphException
    {
        GraphNodes<GraphEdge> currentNode[]= nodes;
        GraphNode node= null;
        /*if (name>=0 && name<currentNode.length)
        {
            if (currentNode[name]!= null && currentNode[name].getData()!= null && areEdgesTheSame(currentNode[name].getData(),getEdge(new GraphNode(name), new GraphNode(name))))
            {
                node= new GraphNode(name);
            }
            if (currentNode[name]!=null)
            {
                if(currentNode[name].getData().firstEndpoint().getName()==name)
                {
                    node= currentNode[name].getData().firstEndpoint();
                }
                else
                {
                    node= currentNode[name].getData().secondEndpoint();
                }
            }
            else
            {
                throw new GraphException("Node with name " + name + " does not exist in the graph.");
            }
        }
        else
        {
            throw new GraphException("There is no node in the graph with name " + name);
        }
        */
        if (name>=0 && name<=graphNodes.length-1)
        {
            node= graphNodes[name];
        }
        else
        {
            throw new GraphException("No node with name " + name + " exists in the graph");
        }
        return node;
    }
    /*
    This method checks and returns true if a node is present in the graph or
    otherwise returns false.    
    */
    private boolean checkGraphNodeExistInTheGraph(GraphNode u)
    {
        //GraphNodes<GraphEdge> currentNode[]= nodes;
        boolean doesGraphNodeExistInTheGraph= false;
        if (u!= null && u.getName()>=0 && u.getName()<=nodes.length-1)
        {
            doesGraphNodeExistInTheGraph= true;
        }
        return doesGraphNodeExistInTheGraph;
    }
    /*
    This method returns the edge connecting two nodes u and v in the graph.
    */
    private boolean areEdgesTheSame(GraphEdge u, GraphEdge v)
    {
        boolean same= false;
        if (u.firstEndpoint().getName()==v.firstEndpoint().getName() && u.secondEndpoint().getName()==v.secondEndpoint().getName())
        {
            if (u.getLabel().equals(v.getLabel()) && u.getType()== v.getType())
            {
                same= true;
            }
        }
        return same;
    }
    /*
    This method returns the edge connecting the two graph nodes u and v. If 
    there exist no edge connecting u and v, this method throws a GraphException.
    This method also throws a GraphException if either u or v does not exist in
    the graph.
    */
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException
    {
        GraphEdge edgeBetweenNodeUAndNodeV= null;
        GraphNodes<GraphEdge> currentNode[]= nodes;
        if (checkGraphNodeExistInTheGraph(u)==false || checkGraphNodeExistInTheGraph(v)==false)
        {
            throw new GraphException("One of the nodes does not exist in the graph");
        }
        while(currentNode!= null && currentNode[u.getName()]!= null && currentNode[u.getName()].getData()!= null)
        {
            if(currentNode[u.getName()].getData().firstEndpoint()!=null && currentNode[u.getName()].getData().secondEndpoint()!=null)
            {
                if (currentNode[u.getName()].getData().firstEndpoint().getName()== u.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==v.getName())
                {
                    edgeBetweenNodeUAndNodeV= currentNode[u.getName()].getData();
                    break;
                }
                else if (currentNode[u.getName()].getData().firstEndpoint().getName()==v.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==u.getName())
                {
                    edgeBetweenNodeUAndNodeV= currentNode[u.getName()].getData();
                    break;
                }
            }
            currentNode[u.getName()]= currentNode[u.getName()].getNext();
        }
        if(currentNode[u.getName()]==null)
        {
            throw new GraphException("There is not edge between " + u.getName() + " and " + v.getName());
        }
        return edgeBetweenNodeUAndNodeV;
    }
    /*
    This method returns an iterator of all edges incident on u or in other words
    it returns an iterator of all edges which contain u as a firstpoint or 
    endpoint. This method throws a GraphException, if u does not exist in the
    graph.
    */
    public Iterator incidentEdges(GraphNode u) throws GraphException
    {
        ArrayList<GraphEdge> arrayListOfEdgesIncidentOnU= new ArrayList<GraphEdge>();
        GraphNodes<GraphEdge> edges[]= nodes;
        
        if (checkGraphNodeExistInTheGraph(u)==false)
        {
            throw new GraphException("Node " + u.getName() + " does not exist in the graph.");
        }
        if (edges[u.getName()]==null)
        {
            return null;
        }
        while(edges[u.getName()]!= null && edges[u.getName()].getNext()!= null && edges[u.getName()].getData()!= null)
        {
            if (edges[u.getName()].getData().firstEndpoint()!=null && edges[u.getName()].getData().secondEndpoint()!=null)
            {
                if (edges[u.getName()].getData().firstEndpoint().getName()==u.getName() || edges[u.getName()].getData().secondEndpoint().getName()==u.getName())
                {
                    arrayListOfEdgesIncidentOnU.add(edges[u.getName()].getData());
                }
            }
            edges[u.getName()]= edges[u.getName()].getNext();
        }
        return arrayListOfEdgesIncidentOnU.iterator();
    }
    /*
    This methods checks and returns true if there is an edge between two graph 
    nodes or otherwise returns false. If either one of the nodes don't exist in 
    the graph, then this method throws a GraphException. 
    */
    public boolean areAdjacent(GraphNode  u, GraphNode v) throws GraphException
    {
        GraphNodes<GraphEdge> currentNode[]= nodes;
        boolean edgeExistBetweenNodeUAndNodeV= false;
        try
        {
            if (checkGraphNodeExistInTheGraph(u)==false || checkGraphNodeExistInTheGraph(v)==false)
            {
                throw new GraphException("One of the nodes don't exist in the tree.");
            }
            while(currentNode[u.getName()]!= null && currentNode[u.getName()].getData()!=null)
            {
                if (currentNode[u.getName()].getData().firstEndpoint().getName()==u.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==v.getName())
                {
                    edgeExistBetweenNodeUAndNodeV= true;
                    break;
                }
                else if (currentNode[u.getName()].getData().firstEndpoint().getName()==v.getName() && currentNode[u.getName()].getData().secondEndpoint().getName()==u.getName())
                {
                    edgeExistBetweenNodeUAndNodeV= true;
                    break;
                }
                currentNode[u.getName()]= currentNode[u.getName()].getNext();
            }
        }
        catch(GraphException e)
        {
            throw new GraphException("One of the nodes don't exist in the tree.");
        }
        return edgeExistBetweenNodeUAndNodeV;
    }
    public static void main (String[] args) {

        /* Exceptions testing */

	System.out.println("");
	System.out.println("======================================================");
	System.out.println("TestGraph");
	System.out.println("======================================================");
	System.out.println("");

	UndirectedGraph G = new UndirectedGraph(1);
        
        
	GraphNode u = new GraphNode(0), v = new GraphNode(1);
	GraphEdge uv;
	Iterator neighbours;
	boolean passed = true;

	try {
	try {
            System.out.println(G.getEdge(u, v));
	    G.insertEdge(u,v,'A');
	    passed = false;
	}
	catch (GraphException e){;}	
	try {
	    u = G.getNode(5);
	    passed = false;
	}
	catch (GraphException e){;}	
	try {
	    uv = G.getEdge(u,v);
	    passed = false;
	}
	catch (GraphException e){;}	
	try {
	    neighbours = G.incidentEdges(v);
	    passed = false;
	}
	catch (GraphException e){;}	
	try {
	    boolean adjacent = G.areAdjacent(u,v);
	    passed = false;
	}
	catch (GraphException e){;}	
	
	u = G.getNode(0);
	G = new UndirectedGraph(4);
	u = G.getNode(0);
	v = G.getNode(1);
	G.insertEdge(u,v,'A');
	v = G.getNode(3);
	G.insertEdge(u,v,'B');
	GraphEdge edge = G.getEdge(u,v);
	if (edge.firstEndpoint().getName() != 0 && edge.firstEndpoint().getName() != 3) passed = false;
	try {
                
		edge = G.getEdge(u,new GraphNode(3));
		passed = false;
	}
	catch (GraphException e){;}
	}
	catch (Exception e) {passed = false;}
        
	print(passed,1);


        /* Create a graph with 9 nodes and 11 edges. Query the graph and check that
           all edges are stored correctly                                           */

        /* Degree of current node */
        int degree;
        int node1, node2;

        int numNodes = 14;

        /* Set of nodes of the graph */
        GraphNode[] V = new GraphNode[numNodes];

        /* Degrees pf the nodes in test graph */
        int NodeDegree[] = {4, 3, 2, 3, 6, 3, 4, 6, 3, 6, 1, 1, 1, 1 };

        /* Adjacency matrix for test graph */
        int M[][] = {{0, 2, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 0
                     {2, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0}, // 1
                     {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // 2
                     {1, 0, 0, 0, 1, 0, 0, 2, 0, 0, 0, 0, 0, 0}, // 3
                     {1, 1, 1, 1, 0, 1, 2, 0, 0, 0, 0, 0, 0, 0}, // 4
                     {0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // 5
                     {0, 0, 0, 0, 2, 1, 0, 1, 2, 0, 0, 0, 0, 0}, // 6
                     {0, 1, 0, 2, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0}, // 7
                     {0, 0, 0, 0, 0, 0, 2, 1, 0, 1, 0, 0, 0, 0}, // 8
                     {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 2, 1, 1, 1}, // 9
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0}, // 10
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 11
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}, // 12
                     {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0}  //13
                    };

	String s;
	int i = 0, j = 0;

        G = new UndirectedGraph(numNodes);

	passed = true;
        try {
	    /* Get all nodes of the graph */
	    for (i = 0; i < numNodes; ++i) {
		V[i] = G.getNode(i);
		if (V[i].getName() != i) passed = false;
	    }

	    V[2].setMark(true);
	    if (V[2].getMark() == false) passed = false;
	    
	    try {
	    	u = G.getNode(numNodes);
	    	passed = false;
	    }
	    catch (GraphException e){;}

	}
	catch (GraphException e) {
		passed = false;
	}
	
	print(passed,2);

	passed = true;
	try {
	/* Insert the edges */
	for (i = 0; i < numNodes; ++i)
	    for (j = 0; j < i; ++j)
		if (M[i][j] == 1) G.insertEdge(V[i],V[j],'A',"door");
		else if (M[i][j] == 2) G.insertEdge(V[i],V[j],'B',"corridor");
	}
	catch (GraphException e) {
	    passed = false;
	}
	print(passed,3);

	passed = true;
	char result;
	try {
	    for (i = 0; i < numNodes; ++i)
		for (j = 0; j < i; ++j) {
		    if (M[i][j] != 0) {
			uv = G.getEdge(V[i],V[j]);
			result = uv.getType();
			if ((M[i][j] == 1 && result != 'A') ||
			    (M[i][j] == 2 && result != 'B')) passed = false;
			u = uv.firstEndpoint();
			if ((u.getName() != i) && u.getName() != j) passed = false;
			v = uv.secondEndpoint();
			if ((v.getName() != i) && v.getName() != j) passed = false;
			if (u.getName() == v.getName()) passed = false;
		    }
		}
	}
	catch (GraphException e) {
	    passed = false;
	}

	print(passed,4);

	passed = true;
	try {
	    for (i = 0; i < numNodes; ++i)
		for (j = 0; j < i; ++j) 
		    if (M[i][j] != 0) {
			if (!G.areAdjacent(V[i],V[j]) || !G.areAdjacent(V[j],V[i]))
			    passed = false;
		    }
	}
	catch (GraphException e) {
	    passed = false;
	}
	
	print(passed,5);

        try {
            for (i = 0; i < numNodes; ++i) {
                u = G.getNode(i);
                neighbours = G.incidentEdges(u);
                degree = 0;
                while (neighbours.hasNext()) {
                    uv = (GraphEdge)neighbours.next();
                    ++degree;
                    node1 = uv.firstEndpoint().getName();
                    node2 = uv.secondEndpoint().getName();

                    if (M[node1][node2] == 0) passed = false;
                    else if ((uv.getType() == 1) && (M[node1][node2] != 1)) passed = false;
                    else if ((uv.getType() == 0) &&(M[node1][node2] != 2))passed = false;
                }
                if (degree != NodeDegree[i]) passed = false;
            }

        }
        catch(GraphException e) {
	    passed = false;
        }
        
        print(passed,6);

    }
    
   /* ======================================================= */
   private static void print(boolean testPassed, int test) {
   /* ======================================================= */  	
	if (testPassed) System.out.println("Test "+test+" passed");
	else System.out.println("Test "+test+" failed");
   }    
}

