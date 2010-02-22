// Fill.asm
// While key is pressed, fills screen
// While no key is pressed, clears screen

@8000
D=A
@count
M=D			// count=8000

@SCREEN
D=A
@addr
M=D			// addr=SCREEN[0]

@INFINITE_LOOP
0;JMP			// goto INFINITE_LOOP

(LOOP)
  @count
  D=M
  @INFINITE_LOOP
  D;JLE		// if count <= 0, goto INFINITE_LOOP

  @0
  D=A-1
  @addr
  A=M
  M=D
  @addr
  D=M
  @1
  D=D+A
  @addr
  M=D
  @count
  M=M-1


  @KBD
  D=M			// D=KBD
  @LOOP
  D;JGT		// if KBD > 0, goto LOOP
  @INFINITE_LOOP
  0;JMP		// else, goto INFINITE_LOOP

(INFINITE_LOOP)
  @KBD
  D=M			// D=KBD
  @LOOP
  D;JGT		// if KBD > 0, goto LOOP

  @8000
  D=A
  @count
  D=D-M
  @INFINITE_LOOP
  D;JLT		// if count > 8000, stop clearing

			// else start clearing screen
  @addr
  A=M
  M=0
  @addr
  D=M
  @1
  D=D-A
  @addr
  M=D
  @count
  M=M+1

  @INFINITE_LOOP
  0;JMP		// else, goto INFINITE_LOOP