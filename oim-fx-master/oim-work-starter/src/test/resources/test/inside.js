function f() {
    var sel = win.document.selection; //IE
    var sel = win.getSelection(); //DOM
    var range = sel.createRange(); // IE下
    var range = sel.getRangeAt(0); // DOM下
    if (range.startContainer) { // DOM下
        sel.removeAllRanges(); // 删除Selection中的所有Range
        range.deleteContents(); // 清除Range中的内容
        // 获得Range中的第一个html结点
        var container = range.startContainer;
        // 获得Range起点的位移
        var pos = range.startOffset;
        // 建一个空Range
        range = document.createRange();
        // 插入内容
        var cons = win.document.createTextNode(",:),");
        if (container.nodeType == 3) {// 如是一个TextNode
            container.insertData(pos, cons.nodeValue);
            // 改变光标位置
            range.setEnd(container, pos + cons.nodeValue.length);
            range.setStart(container, pos + cons.nodeValue.length);
        } else {// 如果是一个HTML Node
            var afternode = container.childNodes[pos];
            container.insertBefore(cons, afternode);
            range.setEnd(cons, cons.nodeValue.length);
            range.setStart(cons, cons.nodeValue.length);
        }
        sel.addRange(range);
    } else {// IE下
        var cnode = range.parentElement();
        while (cnode.tagName.toLowerCase() != 'body') {
            cnodecnode = cnode.parentNode;
        }
        if (cnode.id && cnode.id == 'rich_txt_editor') {
            range.pasteHTML(",:),");
        }
    }
    win.focus();
}
