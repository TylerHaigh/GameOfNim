ROOT=../../
OUTPUT_DIR=$(ROOT)bin/nim
CC=javac
CC_OPTS=-d $(OUTPUT_DIR)
SOURCES=*.java
JAR=NIM.jar
JAR_PATH=$(ROOT)NIM.jar
WIN_RUN=$(ROOT)RunWindows.bat
UNIX_RUN=$(ROOT)RunUnix.sh
ZIP=$(ROOT)NIM.zip

all: build

build:
	mkdir -p $(OUTPUT_DIR) && $(CC) $(CC_OPTS) $(SOURCES)
	jar cvf $(JAR_PATH) $(SOURCES) 
	zip -r $(ZIP) $(JAR_PATH) $(WIN_RUN) $(UNIX_RUN)

clean:
	rm -rf $(OUTPUT_DIR)
