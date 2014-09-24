ROOT=../../
OUTPUT_DIR=$(ROOT)bin/nim
CC=javac
CC_OPTS=-d $(OUTPUT_DIR)
SOURCES=*.java
JAR=$(ROOT)NIM.jar

all: build

build:
	mkdir -p $(OUTPUT_DIR) && $(CC) $(CC_OPTS) $(SOURCES)
	jar cvf $(JAR) $(SOURCES) 

clean:
	rm -rf $(OUTPUT_DIR)
