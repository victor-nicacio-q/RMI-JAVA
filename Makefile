SRC_DIR := src
OUT_DIR := out

SRCS := $(SRC_DIR)

CLS := $(SRCS:$(SRC_DIR)/%.java=$(OUT_DIR)/%.class)

JC := javac
JCFLAGS := -d $(OUT_DIR)/ -cp $(SRC_DIR)/

.SUFFIXES: .java .class

all: $(CLS)
	$(CLS): $(OUT_DIR)/%.class: $(SRC_DIR)/%.java

clean:
	rm $(OUT_DIR)/*.class
