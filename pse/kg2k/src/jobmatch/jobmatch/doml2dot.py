# Visualize the contents of an Enhydra-DODS .doml file.
#
# This script produces output in the .dot format which
# can be used with the graph drawing tool dot and dotty.
# (see http://www.research.att.com/sw/tools/graphviz/)
#
# Author: Michael.Locher@gmx.ch
# Requires: Python >= 1.5.2
#
# !! this is OpenSource !!
# PYTHON RULES.

# $ID:$

from xmllib import XMLParser

class Converter(XMLParser):
    def __init__(self, filename):
        XMLParser.__init__(self)
        self.elements = { 'doml': (self.handleStartDoml,
                                   self.handleStopDoml),
                          'table': (self.handleStartTable,
                                    self.handleStopTable),
                          'column': (self.handleStartColumn,
                                     self.dummy),
                          'type': (self.handleStartType,
                                   self.dummy),
                          'referenceObject': (self.handleStartReference,
                                              self.dummy)
                          }
        self.feed(open(filename).read())
        
    def handleStartDoml(self,attr):
        print 'digraph G {'

    def handleStopDoml(self):
        print '}'        
        
    def handleStartTable(self, attr):
        self.currentTable = Table()
        self.currentTable.name = attr['id']
        self.currentTable.data = attr
        
    def handleStopTable(self):
        print self.currentTable.toDot()

    def handleStartColumn(self, attr):
        self.currentColumn = Column()
        self.currentTable.columns.append(self.currentColumn)
        self.currentColumn.name = attr['id']
        self.currentColumn.table = self.currentTable
        self.currentColumn.data = attr
        self.currentColumn.reference = None

    def handleStartType(self, attr):
        self.currentColumn.type = attr

    def handleStartReference(self, attr):
        self.currentColumn.reference = attr

    def dummy(self):
        pass


class Table:
    def __init__(self):
        self.columns = []

    def id(self):
        return '"%s"' % self.name

    def toDot(self):
        result = '\t%s [shape=box, color=blue, fontsize=12];\n' % self.id()
        if self.data.has_key('extensionOf'):
            result = result + '\t%s -> "%s" [label = "<<Extends>>", color=green, fontsize=8];\n' % (self.id(), self.data['extensionOf'])
        
        for column in self.columns:
            if column.reference == None:
                result = result + '\t\t%s [label= "%s", fontsize=8];\n' % (column.id(), column.name)
                result = result + '\t\t\t%s -> %s [label = "%s", fontsize=8];\n' % (self.id(), column.id(), column.type['javaType'])
            else:
                result = result + '\t\t\t%s -> "%s" [label = "%s", color=red, fontsize=8];\n' % (self.id(), column.reference['reference'], column.name)
                
        return result

class Column:
    def id(self):
        return '"%s_%s"' % (self.table.name, self.name)


if __name__ == '__main__':
    import sys
    Converter(sys.argv[1])
