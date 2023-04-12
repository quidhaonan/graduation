// 直接抄的老师的，此处为 Room.vue 中导出 excel 的

import fs from 'file-saver'
import * as XLSX from 'xlsx'

// 导出 excel 文件
// 该方法需要传 3 个参数，分别是：json格式的数据，表头对象，文件名
export function xlsx(json,fields,filename='表格'){
    // 导出 xlsx
    json.forEach((item)=>{
        for(let i in item){
            if(Object.prototype.hasOwnProperty.call(fields, i)){
                item[fields[i]]=item[i]
            }
            //删除原先的对象属性
            delete item[i]
        }
    })

    // excel 的文件名称
    let sheetName=filename
    // 工作簿对象包含一个 SheetNames 数组，以及一个表对象映射表名称到表对象。（后面还有）
    let wb=XLSX.utils.book_new()
    // 将 JS 对象数组转换为工作表
    let ws=XLSX.utils.json_to_sheet(json,{ header:Object.values(fields) })
    wb.SheetNames.push(sheetName)
    wb.Sheets[sheetName]=ws
    const defaultCellStyle={
        font:{ name:"Verdana",sz:13,color:"FF00FF88" },
        fill:{ fgColor:{ rgb:"FFFFAA00"}}
    }
    // 设置表格的形式
    const wopts={
        bookType:"xlsx",
        bookSST:false,
        type:"binary",
        cellStyles:true,
        defaultCellStyle:defaultCellStyle,
        showGridLines:false
    }
    // 写入的样式
    let wbout=XLSX.write(wb,wopts)
    let blob=new Blob([s2ab(wbout)],{ type:"application/octet-stream" })
    fs.saveAs(blob,filename+".xlsx")
}

const s2ab=(s)=>{
    let buf
    if(typeof ArrayBuffer !== "undefined"){
        buf=new ArrayBuffer(s.length)
        let view=new Uint8Array(buf)
        for(let i=0;i!=s.length;++i) view[i]=s.charCodeAt(i) & 0xff
        return buf
    }else{
        buf=new Array(s.length)
        for(let i=0;i!=s.length;++i) buf[i]=s.charCodeAt(i) & 0xff
        return buf
    }
}