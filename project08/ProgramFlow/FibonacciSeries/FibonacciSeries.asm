// push ARG 1
@ARG
D=M
@1
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// pop 3 1
@3
D=A
@1
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D// push constant 0
@0
D=A
@SP
M=M+1
A=M-1
M=D
// pop THAT 0
@THAT
D=M
@0
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// push constant 1
@1
D=A
@SP
M=M+1
A=M-1
M=D
// pop THAT 1
@THAT
D=M
@1
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// push ARG 0
@ARG
D=M
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push constant 2
@2
D=A
@SP
M=M+1
A=M-1
M=D
// x - y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M-D// pop ARG 0
@ARG
D=M
@0
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// label FibonacciSeries$MAIN_LOOP_START
(FibonacciSeries$MAIN_LOOP_START)
// push ARG 0
@ARG
D=M
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// if-goto FibonacciSeries$COMPUTE_ELEMENT
@SP
M=M-1
A=M
D=M
@FibonacciSeries$COMPUTE_ELEMENT
D;JNE
// goto FibonacciSeries$END_PROGRAM
@FibonacciSeries$END_PROGRAM
0;JMP
// label FibonacciSeries$COMPUTE_ELEMENT
(FibonacciSeries$COMPUTE_ELEMENT)
// push THAT 0
@THAT
D=M
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push THAT 1
@THAT
D=M
@1
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M+D// pop THAT 2
@THAT
D=M
@2
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// push 3 1
@3
D=A
@1
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push constant 1
@1
D=A
@SP
M=M+1
A=M-1
M=D
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M+D// pop 3 1
@3
D=A
@1
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D// push ARG 0
@ARG
D=M
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push constant 1
@1
D=A
@SP
M=M+1
A=M-1
M=D
// x - y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M-D// pop ARG 0
@ARG
D=M
@0
D=D+A
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// goto FibonacciSeries$MAIN_LOOP_START
@FibonacciSeries$MAIN_LOOP_START
0;JMP
// label FibonacciSeries$END_PROGRAM
(FibonacciSeries$END_PROGRAM)
