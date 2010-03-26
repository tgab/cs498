// push constant 17
@17
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 17
@17
D=A
@SP
M=M+1
A=M-1
M=D
// x = y ?
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D

@TRUE0
D;JEQ
D=0
@END_EQ0
0;JMP

(TRUE0)
D=-1

(END_EQ0)
@SP
A=M-1
M=D// push constant 892
@892
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 891
@891
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

@TRUE1
D;JLT
D=0
@END_EQ1
0;JMP

(TRUE1)
D=-1

(END_EQ1)
@SP
A=M-1
M=D// push constant 32767
@32767
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 32766
@32766
D=A
@SP
M=M+1
A=M-1
M=D
// x > y ?
@SP
M=M-1
A=M
D=M
A=A-1
D=M-D

@TRUE2
D;JGT
D=0
@END_EQ2
0;JMP

(TRUE2)
D=-1

(END_EQ2)
@SP
A=M-1
M=D
// push constant 56
@56
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 31
@31
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 53
@53
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
M=M+D// push constant 112
@112
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
M=M-D// -y
@SP
A=M-1
M=-M// x & y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=D&M// push constant 82
@82
D=A
@SP
M=M+1
A=M-1
M=D
// x | y
@SP
M=M-1
A=M
D=M
@SP
A=M-1
M=D|M