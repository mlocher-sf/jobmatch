# Rapid protoype for the matching problem
# $Id: matchtree.py,v 1.1 2000/04/27 08:54:53 locher Exp $

class Node:
    def __init__(self, parent):
        assert isinstance(parent, Node)
        self.setParent(parent)
        self.children = []
    
    def match(self, candidate):
        pass

    def getChildren(self):
        return self.children

    def setParent(self, node):
        self.parent = node

    def isRoot(self):
        return self.parent.__class__ == Root

#-----------------------------------------------------------------------------
class Root(Node):
    def __init__(self):
        Node.__init__(self, self)
        
ROOT = Root()        
#-----------------------------------------------------------------------------
class ElementaryNode(Node):
    pass

class BoolNode(ElementaryNode):
    def __init__(self, value=1, parent=ROOT):
        Node.__init__(self, parent)
        self.value = value

    def match(self, candidate):
        return self.value

    def __str__(self):
        if self.value:
            return 'TRUE'
        else:
            return 'FALSE'

#------------------------------------------------------------------------------
class NOTNode(Node):
    def __init__(self, node, parent=ROOT):
        Node.__init__(self, parent)
        self.getChildren().append(node)
        self.child = node
    
    def match(self, candidate):
        return not self.child.match(candidate)

    def __str__(self):
        return '{NOT: ' + str(self.child) + ' }'


class OperationNode(Node):
    def __init__(self, parent=ROOT):
        Node.__init__(self, parent)

    def add(self, node):
        assert isinstance(node, Node)
        self.getChildren().append(node)
    
    def delimiter(self):
        return ', '

    def __str__(self):
        result = ''
        for child in self.getChildren():
            result = result + str(child) + self.delimiter()
        return result[:-len(self.delimiter())]

class ORNode(OperationNode):
    def match(self, candidate):
        for child in self.getChildren():
            if child.match(candidate):
                return 1
        return 0

    def delimiter(self):
        return ' | '

    def __str__(self):
        return '{OR: ' + OperationNode.__str__(self) + ' }'

class ANDNode(OperationNode):
    def match(self, candidate):
        for child in self.getChildren():
            if not child.match(candidate):
                return 0
        return 1    

    def delimiter(self):
        return ' & '

    def __str__(self):
        return '{AND: ' + OperationNode.__str__(self) + ' }'
    


#------------------------------------------------------------------------------
class MatchTree:
    def __init__(self):
        self.root = ROOT

    def match(self, candidate):
        return self.root.match(candidate)

    def newRoot(self, node):
        node.setParent(ROOT)
        self.root = node

    def __str__(self):
        return 'TREE:\n' + str(self.root)


if __name__ == '__main__':
    candidate = 'a candidate'
    tree = MatchTree()

    ornode = ORNode()
    tn = BoolNode(1)
    fn = BoolNode(0)

    for i in xrange(3):
        ornode.add(tn)
        ornode.add(NOTNode(fn))

    andnode = ANDNode()
    andnode.add(ornode)
    andnode.add(ornode)
    andnode.add(NOTNode(tn))
    
    tree.newRoot(andnode)

    print '%s \nevaluates to %s' % (tree, str(tree.match(candidate)))
    
    
