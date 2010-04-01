// init
@256
D=A
@SP
M=D
// call Sys.init
@0
D=A
@SP
M=M+1
A=M-1
M=D
// save LCL
@LCL
D=M
@SP
M=M+1
A=M-1
M=D
// save ARG
@ARG
D=M
@SP
M=M+1
A=M-1
M=D
// save THIS
@THIS
D=M
@SP
M=M+1
A=M-1
M=D
// save THAT
@THAT
D=M
@SP
M=M+1
A=M-1
M=D
// reposition ARG and LCL
@SP
D=M
@LCL
M=D
@0
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Sys.init
0;JMP


// function Main.fibonacci 0
(Main.fibonacci)

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
// x < y ?
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D

@TRUE0
D;JLT
D=0
@END_EQ0
0;JMP

(TRUE0)
D=-1

(END_EQ0)
@SP
A=M-1
M=D
// if-goto Main.fibonacci$IF_TRUE
@SP
M=M-1
A=M
D=M
@Main.fibonacci$IF_TRUE
D;JNE
// goto Main.fibonacci$IF_FALSE
@Main.fibonacci$IF_FALSE
0;JMP
// label Main.fibonacci$IF_TRUE
(Main.fibonacci$IF_TRUE)
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
// return
// make temp var with location of LCL
@LCL
D=M
@frame
M=D
// put return address in temp var
@5
D=D-A
A=D
D=M
@return
M=D
// reposition return value (pop ARG 0)
@ARG
D=M
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// restore SP
@ARG
D=M
D=D+1
@SP
M=D
// restore THAT
@frame
D=M-1
A=D
D=M
@THAT
M=D
// restore THIS
@frame
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
// restore ARG
@frame
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
// restore LCL
@frame
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
// goto return
@return
A=M
0;JMP
// label Main.fibonacci$IF_FALSE
(Main.fibonacci$IF_FALSE)
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
M=M-D
// call Main.fibonacci 1
@Main.fibonacci.return0
D=A
@SP
M=M+1
A=M-1
M=D
// save LCL
@LCL
D=M
@SP
M=M+1
A=M-1
M=D
// save ARG
@ARG
D=M
@SP
M=M+1
A=M-1
M=D
// save THIS
@THIS
D=M
@SP
M=M+1
A=M-1
M=D
// save THAT
@THAT
D=M
@SP
M=M+1
A=M-1
M=D
// reposition ARG and LCL
@SP
D=M
@LCL
M=D
@1
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Main.fibonacci
0;JMP
(Main.fibonacci.return0)
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
M=M-D
// call Main.fibonacci 1
@Main.fibonacci.return1
D=A
@SP
M=M+1
A=M-1
M=D
// save LCL
@LCL
D=M
@SP
M=M+1
A=M-1
M=D
// save ARG
@ARG
D=M
@SP
M=M+1
A=M-1
M=D
// save THIS
@THIS
D=M
@SP
M=M+1
A=M-1
M=D
// save THAT
@THAT
D=M
@SP
M=M+1
A=M-1
M=D
// reposition ARG and LCL
@SP
D=M
@LCL
M=D
@1
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Main.fibonacci
0;JMP
(Main.fibonacci.return1)
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=D+M
// return
// make temp var with location of LCL
@LCL
D=M
@frame
M=D
// put return address in temp var
@5
D=D-A
A=D
D=M
@return
M=D
// reposition return value (pop ARG 0)
@ARG
D=M
@location
M=D
@SP
M=M-1
A=M
D=M
@location
A=M
M=D
// restore SP
@ARG
D=M
D=D+1
@SP
M=D
// restore THAT
@frame
D=M-1
A=D
D=M
@THAT
M=D
// restore THIS
@frame
D=M
@2
D=D-A
A=D
D=M
@THIS
M=D
// restore ARG
@frame
D=M
@3
D=D-A
A=D
D=M
@ARG
M=D
// restore LCL
@frame
D=M
@4
D=D-A
A=D
D=M
@LCL
M=D
// goto return
@return
A=M
0;JMP
// function Sys.init 0
(Sys.init)

// push constant 4
@4
D=A
@SP
M=M+1
A=M-1
M=D
// call Main.fibonacci 1
@Main.fibonacci.return2
D=A
@SP
M=M+1
A=M-1
M=D
// save LCL
@LCL
D=M
@SP
M=M+1
A=M-1
M=D
// save ARG
@ARG
D=M
@SP
M=M+1
A=M-1
M=D
// save THIS
@THIS
D=M
@SP
M=M+1
A=M-1
M=D
// save THAT
@THAT
D=M
@SP
M=M+1
A=M-1
M=D
// reposition ARG and LCL
@SP
D=M
@LCL
M=D
@1
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Main.fibonacci
0;JMP
(Main.fibonacci.return2)
// label Sys.init$WHILE
(Sys.init$WHILE)
// goto Sys.init$WHILE
@Sys.init$WHILE
0;JMP
