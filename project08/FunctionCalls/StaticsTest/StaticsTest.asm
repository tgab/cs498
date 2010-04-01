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


// function Class1.set 0
(Class1.set)

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
// pop static 0
@SP
M=M-1
A=M
D=M
@Class1.vm.0
M=D
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
// pop static 1
@SP
M=M-1
A=M
D=M
@Class1.vm.1
M=D
// push constant 0
@0
D=A
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
// function Class1.get 0
(Class1.get)

// push static 0
@Class1.vm.0
D=M
@SP
M=M+1
A=M-1
M=D
// push static 1
@Class1.vm.1
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
// function Class2.set 0
(Class2.set)

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
// pop static 0
@SP
M=M-1
A=M
D=M
@Class2.vm.0
M=D
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
// pop static 1
@SP
M=M-1
A=M
D=M
@Class2.vm.1
M=D
// push constant 0
@0
D=A
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
// function Class2.get 0
(Class2.get)

// push static 0
@Class2.vm.0
D=M
@SP
M=M+1
A=M-1
M=D
// push static 1
@Class2.vm.1
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
// function Sys.init 0
(Sys.init)

// push constant 6
@6
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 8
@8
D=A
@SP
M=M+1
A=M-1
M=D
// call Class1.set 2
@Class1.set.return0
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
@2
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Class1.set
0;JMP
(Class1.set.return0)
// pop 5 0
@5
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
M=D// push constant 23
@23
D=A
@SP
M=M+1
A=M-1
M=D
// push constant 15
@15
D=A
@SP
M=M+1
A=M-1
M=D
// call Class2.set 2
@Class2.set.return1
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
@2
D=D-A
@5
D=D-A
@ARG
M=D
// transfer control
@Class2.set
0;JMP
(Class2.set.return1)
// pop 5 0
@5
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
M=D// call Class1.get 0
@Class1.get.return2
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
@Class1.get
0;JMP
(Class1.get.return2)
// call Class2.get 0
@Class2.get.return3
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
@Class2.get
0;JMP
(Class2.get.return3)
// label Sys.init$WHILE
(Sys.init$WHILE)
// goto Sys.init$WHILE
@Sys.init$WHILE
0;JMP
