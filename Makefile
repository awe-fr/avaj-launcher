COMPILE = find * -name "*.java" > sources.txt && javac @sources.txt

JAVA = java

RM = rm -f

all:
		$(COMPILE)

run:	all
		$(JAVA) sources.MainApp "./scenario.txt"

clean:
		find . -name "*.class" -exec $(RM) {} +
		$(RM) sources.txt