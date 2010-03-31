// push constant 0
@0
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
// label BasicLoop$LOOP_START
(BasicLoop$LOOP_START)
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
// x + y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=M+D// pop LCL 0
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
// if-goto BasicLoop$LOOP_START
@SP
M=M-1
A=M
D=M
@BasicLoop$LOOP_START
D;JNE
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
