// function SimpleFunction.test 2
(SimpleFunction.test)

@SP
M=M+1
A=M-1
M=0

@SP
M=M+1
A=M-1
M=0

// push LCL 0
@LCL
D=M
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push LCL 1
@LCL
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
M=D+M
// !y
@SP
A=M-1
M=!M
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
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=D+M
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
// x - y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M-D
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