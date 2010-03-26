// push constant 10
@10
D=A
@SP
M=M+1
A=M-1
M=D
// pop LCL 0
@LCL
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
// push constant 21
@21
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 22
@22
D=A
@SP
M=M+1
A=M-1
M=D
// pop ARG 2
@ARG
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
// pop ARG 1
@ARG
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
// push constant 36
@36
D=A
@SP
M=M+1
A=M-1
M=D
// pop THIS 6
@THIS
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
// push constant 42
@42
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 45
@45
D=A
@SP
M=M+1
A=M-1
M=D
// pop THAT 5
@THAT
D=M
@5
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
// pop THAT 2
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
// push constant 510
@510
D=A
@SP
M=M+1
A=M-1
M=D
// pop 5 6
@5
D=A
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
M=D// push LCL 0
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
// push THAT 5
@THAT
D=M
@5
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
M=M+D// push ARG 1
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
M=M-D// push THIS 6
@THIS
D=M
@6
D=D+A
A=D
D=M
@SP
M=M+1
A=M-1
M=D
// push THIS 6
@THIS
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
M=M+D// x - y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M-D// push 5 6
@5
D=A
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