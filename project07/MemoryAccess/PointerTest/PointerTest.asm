// push constant 3030
@3030
D=A
@SP
M=M+1
A=M-1
M=D
// pop 3 0
@3
D=A
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
M=D// push constant 3040
@3040
D=A
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
M=D// push constant 32
@32
D=A
@SP
M=M+1
A=M-1
M=D
// pop THIS 2
@THIS
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
// push constant 46
@46
D=A
@SP
M=M+1
A=M-1
M=D
// pop THAT 6
@THAT
D=M
@6
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
// push 3 0
@3
D=A
@0
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
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
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M+D// push THIS 2
@THIS
D=M
@2
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
M=M-D// push THAT 6
@THAT
D=M
@6
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
M=M+D