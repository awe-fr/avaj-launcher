COMPILE = find * -name "*.java" > sources.txt && javac @sources.txt

JAVA = java

RM = rm -f

all:
		$(COMPILE)

run:	all
		$(JAVA) sources.MainApp "./simulation.txt"

clean:
		find . -name "*.class" -exec $(RM) {} +
		$(RM) sources.txt