const readView = {

    insertBeforeHtml(html) {
        const element = document.createElement('div');
        element.innerHTML = html;
        document.body.insertBefore(element, document.body.firstElementChild);
    },

    insertLastHtml(html) {
        const element = document.createElement('div');
        element.innerHTML = html;
        document.body.appendChild(element);
    },

    replaceElementHtml(id, html) {
        const newElement = document.createElement('div');
        newElement.innerHTML = html;
        const oldElement = document.getElementById(id);
        document.body.replaceChild(newElement, oldElement);
    },

    setBodyHtml(html) {
        document.body.innerHTML = html;
    },

    checkElementSize(max) {
        if (!max) {
            max = 500;
        }
        const nodes = document.body.children;
        const length = nodes.length;
        if (length > max) {
            const size = length - max;
            for (let i = 0; i < size; i++) {
                document.body.removeChild(document.body.firstElementChild);
            }
        }
    },

    hasElement(id) {
        let has = false;
        const e = document.getElementById(id);
        if (e) {
            has = true;
        }
        return has;
    },


    replaceImageSrc(id, src) {
        const e = document.getElementById(id);
        if (e) {
            e.src = src;
        }
    },

    getScrollPosition() {
        const body = document.body;
        const doc = document.documentElement;

        const height = body.scrollHeight;
        const top = body.scrollTop;

        const clientHeight = body.clientHeight;
        let position = "";

        const a = (height - top);
        const b = (clientHeight + 10);

        if (a < b) {
            position = "bottom";
        } else if (top === 0) {
            position = "top";
        } else {
            position = "middle";
        }
        return position;
    },

    scrollToBottom() {
        const body = document.body;
        const height = body.scrollHeight;
        body.scrollTop = height;
    }
}
