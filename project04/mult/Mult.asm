// Mult.asm
// Computes R0*R1 and stores result in R2

@count
M=0		// Set count=0
@R2
M=0		// Set R2=0

(LOOP)
  @R1
  D=M		// D=R1
  @count
  D=M-D	// D=count-R1
  @END
  D;JGE	// If (count-R1)>= 0 goto END
  @R0
  D=M		// D=R0
  @R2
  M=M+D	// R2=R2+R0
  @count
  M=M+1	// count++
  @LOOP
  0;JMP	// goto LOOP

(END)
  @END
  0;JMP