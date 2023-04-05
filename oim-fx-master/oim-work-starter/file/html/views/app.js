const items = [];

const item = new Item();
item.type = 'image';
item.value = {
    // url: 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww2.sinaimg.cn%2Fthumb180%2F8396074agw1em9fl26o4jj20y30gtgor.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616141694&t=f5091529eac71aa55c14b0085d50038a',
    url: 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg',
};

const section = new Section();
section.items.push(item);

const data = new MessageContentWrap();
// data.avatar = 'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fww2.sinaimg.cn%2Fthumb180%2F8396074agw1em9fl26o4jj20y30gtgor.jpg&refer=http%3A%2F%2Fwww.sina.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616141694&t=f5091529eac71aa55c14b0085d50038a';
data.avatar = 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg';
data.name = '小龙女';
data.nameVisible = true;
data.content = new Content();
data.content.sections.push(section);

items.push(data);

for (let i = 0; i < 1; i++) {
    const item = new Item();
    item.type = 'text';
    item.value = '你好啊！';

    const section = new Section();
    section.items.push(item);

    const data = new MessageContentWrap();
    data.avatar = 'https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2605426497,471263779&fm=26&gp=0.jpg';
    data.name = '小龙女';
    data.nameVisible = true;
    data.content = new Content();
    data.timeText = '2020-01-01';
    data.timeVisible = true;
    data.isOwn = (i % 2 === 0);
    data.content.sections.push(section);

    items.push(data);
}

let app = new Vue({
    el: '#app',
    components: {},
    data: function () {
        return {
            messages: items,
        }
    },
    computed: {
        show() {
        }
    },
    methods: {
        isJson(value) {
            if (typeof (value) === 'string') {
                try {
                    let o = JSON.parse(value);
                    if (typeof (o) === 'object' && o) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (e) {
                    return false;
                }
            }
        },
        itemToObject(item) {
            if (item.type !== 'text') {
                if (this.isJson(item.value)) {
                    item.value = jsonToObject(item.value);
                }
            }
            return item;
        },
        hasSections() {
            const data = this.data;
            let has = false;
            if (data && data.content && data.content.sections && data.content.sections.length > 0) {
                has = true;
            }
            return has;
        },
        resend() {
            const data = this.data;
            if (typeof data.resend === 'function') {
                this.data.resend(this.data.content);
            }
        },
        hasItems(section) {
            const own = this;
            const has = (section && section.items && section.items.length > 0);
            if (has) {
                for (let item of section.items) {
                    own.itemToObject(item);
                }
            }
            return has;
        },
        onDelete() {
            let own = this;
            this.$confirm('此操作将永久删除, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {

            }).catch(() => {

            });
        },
    },
    mounted() {
        // this.initialize();
    }
});

let appPrompt = {};

appPrompt.message = function (text) {
    app.$message(text);
};

appPrompt.success = function (text) {
    app.$message({
        message: text,
        type: 'success'
    });
};
appPrompt.warning = function (text) {
    app.$message({
        message: text,
        type: 'warning'
    });
};
appPrompt.error = function (text) {
    app.$message.error(text);
};

promptBox = appPrompt;
