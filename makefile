SHAPES = Shape.class Quadrilateral.class Square.class Rectangle.class Triangle.class Equilateral.class Right.class Scalene.class Circle.class

all:  Hmwk5.class

bnr:
	make build
	make run

build:
	make clean
	make

clean:
	rm *.class

run:
	java Hmwk5 
	
Hmwk5.class : Hmwk5.java Background.class
	javac Hmwk5.java	

Background.class: Background.java ShapeIO.class $(SHAPES)
	javac Background.java

ShapeIO.class: ShapeIO.java $(SHAPES)
	javac ShapeIO.java

# Shapes

Shape.class: Shape.java
	javac Shape.java

Circle.class: Circle.java Shape.class CircleDialog.class
	javac Circle.java

Quadrilateral.class: Quadrilateral.java Shape.class
	javac Quadrilateral.java

Square.class: Square.java Quadrilateral.class SquareDialog.class
	javac Square.java

Rectangle.class: Rectangle.java Quadrilateral.class RectangleDialog.class
	javac Rectangle.java

Triangle.class: Triangle.java Shape.class
	javac Triangle.java

Equilateral.class: Equilateral.java Triangle.class EquilateralDialog.class
	javac Equilateral.java

Right.class: Right.java Triangle.class RightDialog.class
	javac Right.java

Scalene.class: Scalene.java Triangle.class ScaleneDialog.class
	javac Scalene.java

# Dialogs

SelectShapeDialog.class : SelectShapeDialog.java
	javac SelectShapeDialog.java

ShapeDialog.class : ShapeDialog.java ColorPanel.class
	javac ShapeDialog.java

CircleDialog.class : CircleDialog.java ShapeDialog.class
	javac CircleDialog.java

EquilateralDialog.class : EquilateralDialog.java ShapeDialog.class
	javac EquilateralDialog.java

RightDialog.class : RightDialog.java ShapeDialog.class
	javac RightDialog.java

ScaleneDialog.class : ScaleneDialog.java ShapeDialog.class
	javac ScaleneDialog.java

SquareDialog.class: SquareDialog.java ShapeDialog.class
	javac SquareDialog.java

RectangleDialog.class: RectangleDialog.java ShapeDialog.class
	javac RectangleDialog.java

# Panels

ColorPanel.class : ColorPanel.java
	javac ColorPanel.java