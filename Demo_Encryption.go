package main

import "fmt"

func main(){
	//密钥
	var a int = 0xF
	//处理之前的明文
	var plainText = ""
	fmt.Println("请任意输入内容")
	fmt.Scanln(&plainText)
	//定义加密后的空字符串
	var cipherText string = ""
	//定义存放字符ASCII码值的数组
	var plainIntTable []int
	//定义存放加密后字符的数组
	var cipherTable []string
	//定义存放解密后字符的数组
	var plainTable []string
	//定义解密后的空字符串
	var plainedText string = ""

	for i:=0;i< len(plainText);i++{
		//将 明文 转换成ASCII码
		plainIntTable=append(plainIntTable, int(plainText[i]))
		//使用 密钥 加密
		plainIntTable[i]+=a
		//将 加密后且转换成字符串的数值 存放至 加密数组
		cipherTable = append(cipherTable,string(plainIntTable[i]))
		//将 加密数组中的数值 赋值给 加密字符串
		cipherText += cipherTable[i]
	}
	fmt.Print("加密之后的数据"+cipherText+"\n")

	for i:=0;i<len(cipherText);i++{
		//将 密文 转换成ASCII码
		plainIntTable=append(plainIntTable,int(cipherText[i]))
		//使用 密钥 解密
		plainIntTable [i] -= a
		//将 解密后且转换成字符串的数值 存放至 解密数组
		plainTable=append(plainTable,string(plainIntTable[i]))
		//将 解密数组 的数值赋值给 解密字符串
		plainedText +=plainTable[i]
	}
	fmt.Print("解密之后的数据"+plainedText+"\n")
	fmt.Print("密钥为",a)
}
