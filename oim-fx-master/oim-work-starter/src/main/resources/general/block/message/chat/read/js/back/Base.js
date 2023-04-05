/******************************************************************************
 * map start                                                                  *
 ******************************************************************************/
function Map() {
    this.container = new Object();
}

Map.prototype.put = function (key, value) {
    this.container[key] = value;
};

Map.prototype.set = function (key, value) {
    this.container[key] = value;
};

Map.prototype.get = function (key) {
    return this.container[key];
};

Map.prototype.has = function (key) {
    return (key in this.container);
};

Map.prototype.containsKey = function (key) {
    return (key in this.container);
};

Map.prototype.keySet = function () {
    var keyset = new Array();
    var count = 0;
    for (var key in this.container) {
        if (key === 'extend') { // 跳过object的extend函数
            continue;
        }
        keyset[count] = key;
        count++;
    }
    return keyset;
};

Map.prototype.keys = function () {
    var keyset = new Array();
    var count = 0;
    for (var key in this.container) {
        if (key === 'extend') { // 跳过object的extend函数
            continue;
        }
        keyset[count] = key;
        count++;
    }
    return keyset;
};

Map.prototype.size = function () {
    var count = 0;
    for (var key in this.container) {
        if (key === 'extend') { // 跳过object的extend函数
            continue;
        }
        count++;
    }
    return count;
};

Map.prototype.remove = function (key) {
    var data = this.container[key];
    delete this.container[key];
    return data;
};

Map.prototype.clear = function () {
    for (var key in this.container) {
        if (key === 'extend') { // 跳过object的extend函数
            continue;
        }
        delete this.container[key];
    }
};

Map.prototype.values = function () {
    var values = new Array();
    var count = 0;
    for (var key in this.container) {
        if (key === 'extend') { // 跳过object的extend函数
            continue;
        }
        values[count] = this.container[key];
        count++;
    }
    return values;
};

Map.prototype.toString = function () {
    var text = '';
    for (var i = 0, keys = this.keySet(), len = keys.length; i < len; i++) {
        text = text + keys[i] + '=' + this.container[keys[i]] + ';\n';
    }
    return text;
};
