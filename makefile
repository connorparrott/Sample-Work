CC = gcc

EXEC_NAME = program1 

LDFLAGS = 
CFLAGS = -Wall

HEADERS = $(wildcard *.h)
SRC = $(wildcard *.c)
OBJ = $(SRC:.c=.o)

$(EXEC_NAME).exe: $(OBJ)
	@echo "Linking to make executable"
	$(CC) -o $@ $(OBJ) $(LDFLAGS)

%.o: %.c $(HEADERS)
	@echo "Compiling"
	$(CC) $(CFLAGS) -c $<

