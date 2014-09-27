ROOT=../../
OUTPUT_DIR=$(ROOT)bin/
CC=javac
CC_OPTS=-d $(OUTPUT_DIR)
SOURCES=*.java
JAR=$(ROOT)NIM.jar
WIN_RUN=$(ROOT)RunWindows.bat
UNIX_RUN=$(ROOT)RunUnix.sh
ZIP=$(ROOT)NIM.zip
MANIFEST=$(ROOT)MANIFEST.MF

all: build

build:
	mkdir -p $(OUTPUT_DIR)
	$(CC) $(CC_OPTS) $(SOURCES)
	jar cmf $(MANIFEST) $(JAR) -C $(OUTPUT_DIR) .
	zip -r $(ZIP) $(JAR) $(WIN_RUN) $(UNIX_RUN)

clean:
	rm -rf $(OUTPUT_DIR)
