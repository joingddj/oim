const webBridge = {

    readView: readView,

    setMessages(value) {
        if (BaseUtil.isJsonArray(value)) {
            const messages = BaseUtil.jsonToObject(value);

        }
        return (value) ? value.toString() : '';
    },

    insertBefore(value) {
        if (BaseUtil.isJsonObject(value)) {

            const message = BaseUtil.jsonToObject(value);

        }
        return (value) ? value.toString() : '';
    },

    insertLast(value) {

        if (BaseUtil.isJsonObject(value)) {

            const message = BaseUtil.jsonToObject(value);
            this.readView.insertLastHtml('<label>和欢呼声</label>');
        }
        return (value) ? value.toString() : '';
    }
}
