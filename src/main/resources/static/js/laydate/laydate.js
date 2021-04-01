/*! layDate v5.1.0 | 日期与时间组件 | The MIT License */
;!function () {
    "use strict";
    var e = "lay", t = window.document, a = function (e) {
        return new n(e)
    }, n = function (e) {
        for (var a = 0, n = "object" == typeof e ? [e] : (this.selector = e, t.querySelectorAll(e || null)); a < n.length; a++) this.push(n[a])
    };
    n.prototype = [], n.prototype.constructor = n, a.extend = function () {
        var e = 1, t = arguments, a = function (e, t) {
            e = e || (t.constructor === Array ? [] : {});
            for (var n in t) e[n] = t[n] && t[n].constructor === Object ? a(e[n], t[n]) : t[n];
            return e
        };
        for (t[0] = "object" == typeof t[0] ? t[0] : {}; e < t.length; e++) "object" == typeof t[e] && a(t[0], t[e]);
        return t[0]
    }, a.v = "1.0.0", a.ie = function () {
        var e = navigator.userAgent.toLowerCase();
        return !!(window.ActiveXObject || "ActiveXObject" in window) && ((e.match(/msie\s(\d+)/) || [])[1] || "11")
    }(), a.getPath = function () {
        var e = t.currentScript ? t.currentScript.src : function () {
            for (var e, a = t.scripts, n = a.length - 1, i = n; i > 0; i--) if ("interactive" === a[i].readyState) {
                e = a[i].src;
                break
            }
            return e || a[n].src
        }();
        return e.substring(0, e.lastIndexOf("/") + 1)
    }, a.stope = function (e) {
        e = e || window.event, e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0
    }, a.each = function (e, t) {
        var a, n = this;
        if ("function" != typeof t) return n;
        if (e = e || [], e.constructor === Object) {
            for (a in e) if (t.call(e[a], a, e[a])) break
        } else for (a = 0; a < e.length && !t.call(e[a], a, e[a]); a++) ;
        return n
    }, a.digit = function (e, t, a) {
        var n = "";
        e = String(e), t = t || 2;
        for (var i = e.length; i < t; i++) n += "0";
        return e < Math.pow(10, t) ? n + (0 | e) : e
    }, a.elem = function (e, n) {
        var i = t.createElement(e);
        return a.each(n || {}, function (e, t) {
            i.setAttribute(e, t)
        }), i
    }, a.getStyle = function (e, t) {
        var a = e.currentStyle ? e.currentStyle : window.getComputedStyle(e, null);
        return a[a.getPropertyValue ? "getPropertyValue" : "getAttribute"](t)
    }, a.link = function (e, n, i) {
        var l = t.getElementsByTagName("head")[0], r = t.createElement("link");
        "string" == typeof n && (i = n);
        var o = (i || e).replace(/\.|\//g, ""), s = "layuicss-" + o, y = 0;
        r.rel = "stylesheet", r.href = e, r.id = s, t.getElementById(s) || l.appendChild(r), "function" == typeof n && !function c() {
            return ++y > 80 ? window.console && console.error(o + ".css: Invalid") : void (1989 === parseInt(a.getStyle(t.getElementById(s), "width")) ? n() : setTimeout(c, 100))
        }()
    }, n.addStr = function (e, t) {
        return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), a.each(t, function (t, a) {
            new RegExp("\\b" + a + "\\b").test(e) || (e = e + " " + a)
        }), e.replace(/^\s|\s$/, "")
    }, n.removeStr = function (e, t) {
        return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), a.each(t, function (t, a) {
            var n = new RegExp("\\b" + a + "\\b");
            n.test(e) && (e = e.replace(n, ""))
        }), e.replace(/\s+/, " ").replace(/^\s|\s$/, "")
    }, n.prototype.find = function (e) {
        var t = this, n = 0, i = [], l = "object" == typeof e;
        return this.each(function (a, r) {
            for (var o = l ? [e] : r.querySelectorAll(e || null); n < o.length; n++) i.push(o[n]);
            t.shift()
        }), l || (t.selector = (t.selector ? t.selector + " " : "") + e), a.each(i, function (e, a) {
            t.push(a)
        }), t
    }, n.prototype.each = function (e) {
        return a.each.call(this, this, e)
    }, n.prototype.addClass = function (e, t) {
        return this.each(function (a, i) {
            i.className = n[t ? "removeStr" : "addStr"](i.className, e)
        })
    }, n.prototype.removeClass = function (e) {
        return this.addClass(e, !0)
    }, n.prototype.hasClass = function (e) {
        var t = !1;
        return this.each(function (a, n) {
            new RegExp("\\b" + e + "\\b").test(n.className) && (t = !0)
        }), t
    }, n.prototype.attr = function (e, t) {
        var a = this;
        return void 0 === t ? function () {
            if (a.length > 0) return a[0].getAttribute(e)
        }() : a.each(function (a, n) {
            n.setAttribute(e, t)
        })
    }, n.prototype.removeAttr = function (e) {
        return this.each(function (t, a) {
            a.removeAttribute(e)
        })
    }, n.prototype.html = function (e) {
        return this.each(function (t, a) {
            a.innerHTML = e
        })
    }, n.prototype.val = function (e) {
        return this.each(function (t, a) {
            a.value = e
        })
    }, n.prototype.append = function (e) {
        return this.each(function (t, a) {
            "object" == typeof e ? a.appendChild(e) : a.innerHTML = a.innerHTML + e
        })
    }, n.prototype.remove = function (e) {
        return this.each(function (t, a) {
            e ? a.removeChild(e) : a.parentNode.removeChild(a)
        })
    }, n.prototype.on = function (e, t) {
        return this.each(function (a, n) {
            n.attachEvent ? n.attachEvent("on" + e, function (e) {
                e.target = e.srcElement, t.call(n, e)
            }) : n.addEventListener(e, t, !1)
        })
    }, n.prototype.off = function (e, t) {
        return this.each(function (a, n) {
            n.detachEvent ? n.detachEvent("on" + e, t) : n.removeEventListener(e, t, !1)
        })
    }, window.lay = a, window.layui && layui.define && layui.define(function (t) {
        t(e, a)
    })
}(), !function (e) {
    "use strict";
    var t = e.layui && layui.define, a = {
            getPath: e.lay && lay.getPath ? lay.getPath() : "", link: function (t, a, i) {
                n.path && e.lay && lay.link && lay.link(n.path + t, a, i)
            }
        }, n = {
            v: "5.1.0", config: {}, index: e.laydate && e.laydate.v ? 1e5 : 0, path: a.getPath, set: function (e) {
                var t = this;
                return t.config = lay.extend({}, t.config, e), t
            }, ready: function (e) {
                var i = "laydate", l = "", r = (t ? "modules/laydate/" : "theme/") + "default/laydate.css?v=" + n.v + l;
                return t ? layui.addcss(r, e, i) : a.link(r, e, i), this
            }
        }, i = function () {
            var e = this;
            return {
                hint: function (t) {
                    e.hint.call(e, t)
                }, config: e.config
            }
        }, l = "laydate", r = ".layui-laydate", o = "layui-this", s = "laydate-disabled", y = "开始时间不能早于结束时间<br>请重新选择",
        c = [100, 2e5], d = "layui-laydate-static", m = "layui-laydate-list", u = "laydate-selected",
        h = "layui-laydate-hint", f = "laydate-day-prev", p = "laydate-day-next", g = "layui-laydate-footer",
        v = ".laydate-btns-confirm", T = "laydate-time-text", D = ".laydate-btns-time", w = function (e) {
            var t = this;
            t.index = ++n.index, t.config = lay.extend({}, t.config, n.config, e), n.ready(function () {
                t.init()
            })
        };
    w.isLeapYear = function (e) {
        return e % 4 === 0 && e % 100 !== 0 || e % 400 === 0
    }, w.prototype.config = {
        type: "date",
        range: !1,
        format: "yyyy-MM-dd",
        value: null,
        isInitValue: !0,
        min: "1900-1-1",
        max: "2099-12-31",
        trigger: "focus",
        show: !1,
        showBottom: !0,
        btns: ["clear", "now", "confirm"],
        lang: "cn",
        theme: "default",
        position: null,
        calendar: !1,
        mark: {},
        zIndex: null,
        done: null,
        change: null
    }, w.prototype.lang = function () {
        var e = this, t = e.config, a = {
            cn: {
                weeks: ["日", "一", "二", "三", "四", "五", "六"],
                time: ["时", "分", "秒"],
                timeTips: "选择时间",
                startTime: "开始时间",
                endTime: "结束时间",
                dateTips: "返回日期",
                month: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
                tools: {confirm: "确定", clear: "清空", now: "现在"}
            },
            en: {
                weeks: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
                time: ["Hours", "Minutes", "Seconds"],
                timeTips: "Select Time",
                startTime: "Start Time",
                endTime: "End Time",
                dateTips: "Select Date",
                month: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                tools: {confirm: "Confirm", clear: "Clear", now: "Now"}
            }
        };
        return a[t.lang] || a.cn
    }, w.prototype.init = function () {
        var e = this, t = e.config, a = "yyyy|y|MM|M|dd|d|HH|H|mm|m|ss|s", n = "static" === t.position,
            i = {year: "yyyy", month: "yyyy-MM", date: "yyyy-MM-dd", time: "HH:mm:ss", datetime: "yyyy-MM-dd HH:mm:ss"};
        t.elem = lay(t.elem), t.eventElem = lay(t.eventElem), t.elem[0] && (t.range === !0 && (t.range = "-"), t.format === i.date && (t.format = i[t.type]), e.format = t.format.match(new RegExp(a + "|.", "g")) || [], e.EXP_IF = "", e.EXP_SPLIT = "", lay.each(e.format, function (t, n) {
            var i = new RegExp(a).test(n) ? "\\d{" + function () {
                return new RegExp(a).test(e.format[0 === t ? t + 1 : t - 1] || "") ? /^yyyy|y$/.test(n) ? 4 : n.length : /^yyyy$/.test(n) ? "1,4" : /^y$/.test(n) ? "1,308" : "1,2"
            }() + "}" : "\\" + n;
            e.EXP_IF = e.EXP_IF + i, e.EXP_SPLIT = e.EXP_SPLIT + "(" + i + ")"
        }), e.EXP_IF = new RegExp("^" + (t.range ? e.EXP_IF + "\\s\\" + t.range + "\\s" + e.EXP_IF : e.EXP_IF) + "$"), e.EXP_SPLIT = new RegExp("^" + e.EXP_SPLIT + "$", ""), e.isInput(t.elem[0]) || "focus" === t.trigger && (t.trigger = "click"), t.elem.attr("lay-key") || (t.elem.attr("lay-key", e.index), t.eventElem.attr("lay-key", e.index)), t.mark = lay.extend({}, t.calendar && "cn" === t.lang ? {
            "0-1-1": "元旦",
            "0-2-14": "情人",
            "0-3-8": "妇女",
            "0-3-12": "植树",
            "0-4-1": "愚人",
            "0-5-1": "劳动",
            "0-5-4": "青年",
            "0-6-1": "儿童",
            "0-9-10": "教师",
            "0-9-18": "国耻",
            "0-10-1": "国庆",
            "0-12-25": "圣诞"
        } : {}, t.mark), lay.each(["min", "max"], function (e, a) {
            var n = [], i = [];
            if ("number" == typeof t[a]) {
                var l = t[a], r = (new Date).getTime(), o = 864e5, s = new Date(l ? l < o ? r + l * o : l : r);
                n = [s.getFullYear(), s.getMonth() + 1, s.getDate()], l < o || (i = [s.getHours(), s.getMinutes(), s.getSeconds()])
            } else n = (t[a].match(/\d+-\d+-\d+/) || [""])[0].split("-"), i = (t[a].match(/\d+:\d+:\d+/) || [""])[0].split(":");
            t[a] = {
                year: 0 | n[0] || (new Date).getFullYear(),
                month: n[1] ? (0 | n[1]) - 1 : (new Date).getMonth(),
                date: 0 | n[2] || (new Date).getDate(),
                hours: 0 | i[0],
                minutes: 0 | i[1],
                seconds: 0 | i[2]
            }
        }), e.elemID = "layui-laydate" + t.elem.attr("lay-key"), (t.show || n) && e.render(), n || e.events(), t.value && t.isInitValue && (t.value.constructor === Date ? e.setValue(e.parse(0, e.systemDate(t.value))) : e.setValue(t.value)))
    }, w.prototype.render = function () {
        var e = this, t = e.config, a = e.lang(), n = "static" === t.position, i = e.elem = lay.elem("div", {
                id: e.elemID,
                "class": ["layui-laydate", t.range ? " layui-laydate-range" : "", n ? " " + d : "", t.theme && "default" !== t.theme && !/^#/.test(t.theme) ? " laydate-theme-" + t.theme : ""].join("")
            }), l = e.elemMain = [], r = e.elemHeader = [], o = e.elemCont = [], s = e.table = [],
            y = e.footer = lay.elem("div", {"class": g});
        if (t.zIndex && (i.style.zIndex = t.zIndex), lay.each(new Array(2), function (e) {
            if (!t.range && e > 0) return !0;
            var n = lay.elem("div", {"class": "layui-laydate-header"}), i = [function () {
                    var e = lay.elem("i", {"class": "layui-icon laydate-icon laydate-prev-y"});
                    return e.innerHTML = "&#xe65a;", e
                }(), function () {
                    var e = lay.elem("i", {"class": "layui-icon laydate-icon laydate-prev-m"});
                    return e.innerHTML = "&#xe603;", e
                }(), function () {
                    var e = lay.elem("div", {"class": "laydate-set-ym"}), t = lay.elem("span"), a = lay.elem("span");
                    return e.appendChild(t), e.appendChild(a), e
                }(), function () {
                    var e = lay.elem("i", {"class": "layui-icon laydate-icon laydate-next-m"});
                    return e.innerHTML = "&#xe602;", e
                }(), function () {
                    var e = lay.elem("i", {"class": "layui-icon laydate-icon laydate-next-y"});
                    return e.innerHTML = "&#xe65b;", e
                }()], y = lay.elem("div", {"class": "layui-laydate-content"}), c = lay.elem("table"), d = lay.elem("thead"),
                m = lay.elem("tr");
            lay.each(i, function (e, t) {
                n.appendChild(t)
            }), d.appendChild(m), lay.each(new Array(6), function (e) {
                var t = c.insertRow(0);
                lay.each(new Array(7), function (n) {
                    if (0 === e) {
                        var i = lay.elem("th");
                        i.innerHTML = a.weeks[n], m.appendChild(i)
                    }
                    t.insertCell(n)
                })
            }), c.insertBefore(d, c.children[0]), y.appendChild(c), l[e] = lay.elem("div", {"class": "layui-laydate-main laydate-main-list-" + e}), l[e].appendChild(n), l[e].appendChild(y), r.push(i), o.push(y), s.push(c)
        }), lay(y).html(function () {
            var e = [], i = [];
            return "datetime" === t.type && e.push('<span lay-type="datetime" class="laydate-btns-time">' + a.timeTips + "</span>"), lay.each(t.btns, function (e, l) {
                var r = a.tools[l] || "btn";
                t.range && "now" === l || (n && "clear" === l && (r = "cn" === t.lang ? "重置" : "Reset"), i.push('<span lay-type="' + l + '" class="laydate-btns-' + l + '">' + r + "</span>"))
            }), e.push('<div class="laydate-footer-btns">' + i.join("") + "</div>"), e.join("")
        }()), lay.each(l, function (e, t) {
            i.appendChild(t)
        }), t.showBottom && i.appendChild(y), /^#/.test(t.theme)) {
            var c = lay.elem("style"),
                m = ["#{{id}} .layui-laydate-header{background-color:{{theme}};}", "#{{id}} .layui-this{background-color:{{theme}} !important;}"].join("").replace(/{{id}}/g, e.elemID).replace(/{{theme}}/g, t.theme);
            "styleSheet" in c ? (c.setAttribute("type", "text/css"), c.styleSheet.cssText = m) : c.innerHTML = m, lay(i).addClass("laydate-theme-molv"), i.appendChild(c)
        }
        e.remove(w.thisElemDate), n ? t.elem.append(i) : (document.body.appendChild(i), e.position()), e.checkDate().calendar(null, 0, "init"), e.changeEvent(), w.thisElemDate = e.elemID, "function" == typeof t.ready && t.ready(lay.extend({}, t.dateTime, {month: t.dateTime.month + 1}))
    }, w.prototype.remove = function (e) {
        var t = this, a = (t.config, lay("#" + (e || t.elemID)));
        return a.hasClass(d) || t.checkDate(function () {
            a.remove(), delete t.endDate
        }), t
    }, w.prototype.position = function () {
        var e = this, t = e.config, a = e.bindElem || t.elem[0], n = a.getBoundingClientRect(), i = e.elem.offsetWidth,
            l = e.elem.offsetHeight, r = function (e) {
                return e = e ? "scrollLeft" : "scrollTop", document.body[e] | document.documentElement[e]
            }, o = function (e) {
                return document.documentElement[e ? "clientWidth" : "clientHeight"]
            }, s = 5, y = n.left, c = n.bottom;
        y + i + s > o("width") && (y = o("width") - i - s), c + l + s > o() && (c = n.top > l ? n.top - l : o() - l, c -= 2 * s), t.position && (e.elem.style.position = t.position), e.elem.style.left = y + ("fixed" === t.position ? 0 : r(1)) + "px", e.elem.style.top = c + ("fixed" === t.position ? 0 : r()) + "px"
    }, w.prototype.hint = function (e) {
        var t = this, a = (t.config, lay.elem("div", {"class": h}));
        t.elem && (a.innerHTML = e || "", lay(t.elem).find("." + h).remove(), t.elem.appendChild(a), clearTimeout(t.hinTimer), t.hinTimer = setTimeout(function () {
            lay(t.elem).find("." + h).remove()
        }, 3e3))
    }, w.prototype.getAsYM = function (e, t, a) {
        return a ? t-- : t++, t < 0 && (t = 11, e--), t > 11 && (t = 0, e++), [e, t]
    }, w.prototype.systemDate = function (e) {
        var t = e || new Date;
        return {
            year: t.getFullYear(),
            month: t.getMonth(),
            date: t.getDate(),
            hours: e ? e.getHours() : 0,
            minutes: e ? e.getMinutes() : 0,
            seconds: e ? e.getSeconds() : 0
        }
    }, w.prototype.checkDate = function (e) {
        var t, a, i = this, l = (new Date, i.config), r = l.dateTime = l.dateTime || i.systemDate(),
            o = i.bindElem || l.elem[0],
            s = (i.isInput(o) ? "val" : "html", i.isInput(o) ? o.value : "static" === l.position ? "" : o.innerHTML),
            y = function (e) {
                e.year > c[1] && (e.year = c[1], a = !0), e.month > 11 && (e.month = 11, a = !0), e.hours > 23 && (e.hours = 0, a = !0), e.minutes > 59 && (e.minutes = 0, e.hours++, a = !0), e.seconds > 59 && (e.seconds = 0, e.minutes++, a = !0), t = n.getEndDate(e.month + 1, e.year), e.date > t && (e.date = t, a = !0)
            }, d = function (e, t, n) {
                var r = ["startTime", "endTime"];
                t = (t.match(i.EXP_SPLIT) || []).slice(1), n = n || 0, l.range && (i[r[n]] = i[r[n]] || {}), lay.each(i.format, function (o, s) {
                    var y = parseFloat(t[o]);
                    t[o].length < s.length && (a = !0), /yyyy|y/.test(s) ? (y < c[0] && (y = c[0], a = !0), e.year = y) : /MM|M/.test(s) ? (y < 1 && (y = 1, a = !0), e.month = y - 1) : /dd|d/.test(s) ? (y < 1 && (y = 1, a = !0), e.date = y) : /HH|H/.test(s) ? (y < 1 && (y = 0, a = !0), e.hours = y, l.range && (i[r[n]].hours = y)) : /mm|m/.test(s) ? (y < 1 && (y = 0, a = !0), e.minutes = y, l.range && (i[r[n]].minutes = y)) : /ss|s/.test(s) && (y < 1 && (y = 0, a = !0), e.seconds = y, l.range && (i[r[n]].seconds = y))
                }), y(e)
            };
        return "limit" === e ? (y(r), i) : (s = s || l.value, "string" == typeof s && (s = s.replace(/\s+/g, " ").replace(/^\s|\s$/g, "")), l.range && (i.endDate = i.endDate || lay.extend({}, r, function () {
            var e = {}, t = i.getAsYM(r.year, r.month);
            return "year" === l.type ? e.year = r.year + 1 : "time" !== l.type && (e.year = t[0], e.month = t[1]), e
        }())), "string" == typeof s && s ? i.EXP_IF.test(s) ? l.range ? (s = s.split(" " + l.range + " "), lay.each([l.dateTime, i.endDate], function (e, t) {
            d(t, s[e], e)
        })) : d(r, s) : (i.hint("日期格式不合法<br>必须遵循下述格式：<br>" + (l.range ? l.format + " " + l.range + " " + l.format : l.format) + "<br>已为你重置"), a = !0) : s && s.constructor === Date ? l.dateTime = i.systemDate(s) : (l.dateTime = i.systemDate(), delete i.startTime, delete i.endTime), y(r), a && s && i.setValue(l.range ? i.endDate ? i.parse() : "" : i.parse()), e && e(), i)
    }, w.prototype.mark = function (e, t) {
        var a, n = this, i = n.config;
        return lay.each(i.mark, function (e, n) {
            var i = e.split("-");
            i[0] != t[0] && 0 != i[0] || i[1] != t[1] && 0 != i[1] || i[2] != t[2] || (a = n || t[2])
        }), a && e.html('<span class="laydate-day-mark">' + a + "</span>"), n
    }, w.prototype.limit = function (e, t, a, n) {
        var i, l = this, r = l.config, o = {}, y = r[a > 41 ? "endDate" : "dateTime"], c = lay.extend({}, y, t || {});
        return lay.each({now: c, min: r.min, max: r.max}, function (e, t) {
            o[e] = l.newDate(lay.extend({year: t.year, month: t.month, date: t.date}, function () {
                var e = {};
                return lay.each(n, function (a, n) {
                    e[n] = t[n]
                }), e
            }())).getTime()
        }), i = o.now < o.min || o.now > o.max, e && e[i ? "addClass" : "removeClass"](s), i
    }, w.prototype.thisDateTime = function (e) {
        var t = this, a = t.config;
        return e ? t.endDate : a.dateTime
    }, w.prototype.calendar = function (e, t, a) {
        var i, l, r, s = this, y = s.config, t = t ? 1 : 0, d = e || s.thisDateTime(t), m = new Date, u = s.lang(),
            h = "date" !== y.type && "datetime" !== y.type, f = lay(s.table[t]).find("td"),
            p = lay(s.elemHeader[t][2]).find("span");
        return d.year < c[0] && (d.year = c[0], s.hint("最低只能支持到公元" + c[0] + "年")), d.year > c[1] && (d.year = c[1], s.hint("最高只能支持到公元" + c[1] + "年")), s.firstDate || (s.firstDate = lay.extend({}, d)), m.setFullYear(d.year, d.month, 1), i = m.getDay(), l = n.getEndDate(d.month || 12, d.year), r = n.getEndDate(d.month + 1, d.year), lay.each(f, function (e, t) {
            var a = [d.year, d.month], n = 0;
            t = lay(t), t.removeAttr("class"), e < i ? (n = l - i + e, t.addClass("laydate-day-prev"), a = s.getAsYM(d.year, d.month, "sub")) : e >= i && e < r + i ? (n = e - i, n + 1 === d.date && t.addClass(o)) : (n = e - r - i, t.addClass("laydate-day-next"), a = s.getAsYM(d.year, d.month)), a[1]++, a[2] = n + 1, t.attr("lay-ymd", a.join("-")).html(a[2]), s.mark(t, a).limit(t, {
                year: a[0],
                month: a[1] - 1,
                date: a[2]
            }, e)
        }), lay(p[0]).attr("lay-ym", d.year + "-" + (d.month + 1)), lay(p[1]).attr("lay-ym", d.year + "-" + (d.month + 1)), "cn" === y.lang ? (lay(p[0]).attr("lay-type", "year").html(d.year + "年"), lay(p[1]).attr("lay-type", "month").html(d.month + 1 + "月")) : (lay(p[0]).attr("lay-type", "month").html(u.month[d.month]), lay(p[1]).attr("lay-type", "year").html(d.year)), h && (y.range ? e && (s.listYM = [[y.dateTime.year, y.dateTime.month + 1], [s.endDate.year, s.endDate.month + 1]], s.list(y.type, 0).list(y.type, 1), "time" === y.type ? s.setBtnStatus("时间", lay.extend({}, s.systemDate(), s.startTime), lay.extend({}, s.systemDate(), s.endTime)) : s.setBtnStatus(!0)) : (s.listYM = [[d.year, d.month + 1]], s.list(y.type, 0))), y.range && "init" === a && !e && s.calendar(s.endDate, 1), y.range || s.limit(lay(s.footer).find(v), null, 0, ["hours", "minutes", "seconds"]), s.setBtnStatus(), s
    }, w.prototype.list = function (e, t) {
        var a = this, n = a.config, i = n.dateTime, l = a.lang(),
            r = n.range && "date" !== n.type && "datetime" !== n.type, y = lay.elem("ul", {
                "class": m + " " + {
                    year: "laydate-year-list",
                    month: "laydate-month-list",
                    time: "laydate-time-list"
                }[e]
            }), c = a.elemHeader[t], d = lay(c[2]).find("span"), u = a.elemCont[t || 0], h = lay(u).find("." + m)[0],
            f = "cn" === n.lang, p = f ? "年" : "", g = a.listYM[t] || {}, w = ["hours", "minutes", "seconds"],
            C = ["startTime", "endTime"][t];
        if (g[0] < 1 && (g[0] = 1), "year" === e) {
            var x, M = x = g[0] - 7;
            M < 1 && (M = x = 1), lay.each(new Array(15), function (e) {
                var i = lay.elem("li", {"lay-ym": x}), l = {year: x};
                x == g[0] && lay(i).addClass(o), i.innerHTML = x + p, y.appendChild(i), x < a.firstDate.year ? (l.month = n.min.month, l.date = n.min.date) : x >= a.firstDate.year && (l.month = n.max.month, l.date = n.max.date), a.limit(lay(i), l, t), x++
            }), lay(d[f ? 0 : 1]).attr("lay-ym", x - 8 + "-" + g[1]).html(M + p + " - " + (x - 1 + p))
        } else if ("month" === e) lay.each(new Array(12), function (e) {
            var i = lay.elem("li", {"lay-ym": e}), r = {year: g[0], month: e};
            e + 1 == g[1] && lay(i).addClass(o), i.innerHTML = l.month[e] + (f ? "月" : ""), y.appendChild(i), g[0] < a.firstDate.year ? r.date = n.min.date : g[0] >= a.firstDate.year && (r.date = n.max.date), a.limit(lay(i), r, t)
        }), lay(d[f ? 0 : 1]).attr("lay-ym", g[0] + "-" + g[1]).html(g[0] + p); else if ("time" === e) {
            var b = function () {
                lay(y).find("ol").each(function (e, n) {
                    lay(n).find("li").each(function (n, i) {
                        a.limit(lay(i), [{hours: n}, {hours: a[C].hours, minutes: n}, {
                            hours: a[C].hours,
                            minutes: a[C].minutes,
                            seconds: n
                        }][e], t, [["hours"], ["hours", "minutes"], ["hours", "minutes", "seconds"]][e])
                    })
                }), n.range || a.limit(lay(a.footer).find(v), a[C], 0, ["hours", "minutes", "seconds"])
            };
            n.range ? a[C] || (a[C] = {
                hours: 0,
                minutes: 0,
                seconds: 0
            }) : a[C] = i, lay.each([24, 60, 60], function (e, t) {
                var n = lay.elem("li"), i = ["<p>" + l.time[e] + "</p><ol>"];
                lay.each(new Array(t), function (t) {
                    i.push("<li" + (a[C][w[e]] === t ? ' class="' + o + '"' : "") + ">" + lay.digit(t, 2) + "</li>")
                }), n.innerHTML = i.join("") + "</ol>", y.appendChild(n)
            }), b()
        }
        if (h && u.removeChild(h), u.appendChild(y), "year" === e || "month" === e) lay(a.elemMain[t]).addClass("laydate-ym-show"), lay(y).find("li").on("click", function () {
            var l = 0 | lay(this).attr("lay-ym");
            if (!lay(this).hasClass(s)) {
                0 === t ? (i[e] = l, a.limit(lay(a.footer).find(v), null, 0)) : a.endDate[e] = l;
                var c = "year" === n.type || "month" === n.type;
                c ? (lay(y).find("." + o).removeClass(o), lay(this).addClass(o), "month" === n.type && "year" === e && (a.listYM[t][0] = l, r && (t ? i.year = l : a.endDate.year = l), a.list("month", t))) : (a.checkDate("limit").calendar(null, t), a.closeList()), a.setBtnStatus(), n.range || (("month" === n.type && "month" === e || "year" === n.type && "year" === e) && a.setValue(a.parse()).remove().done(), a.done(null, "change")), lay(a.footer).find(D).removeClass(s)
            }
        }); else {
            var E = lay.elem("span", {"class": T}), k = function () {
                lay(y).find("ol").each(function (e) {
                    var t = this, n = lay(t).find("li");
                    t.scrollTop = 30 * (a[C][w[e]] - 2), t.scrollTop <= 0 && n.each(function (e, a) {
                        if (!lay(this).hasClass(s)) return t.scrollTop = 30 * (e - 2), !0
                    })
                })
            }, H = lay(c[2]).find("." + T);
            k(), E.innerHTML = n.range ? [l.startTime, l.endTime][t] : l.timeTips, lay(a.elemMain[t]).addClass("laydate-time-show"), H[0] && H.remove(), c[2].appendChild(E), lay(y).find("ol").each(function (e) {
                var t = this;
                lay(t).find("li").on("click", function () {
                    var l = 0 | this.innerHTML;
                    lay(this).hasClass(s) || (n.range ? a[C][w[e]] = l : i[w[e]] = l, lay(t).find("." + o).removeClass(o), lay(this).addClass(o), b(), k(), (a.endDate || "time" === n.type) && a.done(null, "change"), a.setBtnStatus())
                })
            })
        }
        return a
    }, w.prototype.listYM = [], w.prototype.closeList = function () {
        var e = this;
        e.config;
        lay.each(e.elemCont, function (t, a) {
            lay(this).find("." + m).remove(), lay(e.elemMain[t]).removeClass("laydate-ym-show laydate-time-show")
        }), lay(e.elem).find("." + T).remove()
    }, w.prototype.setBtnStatus = function (e, t, a) {
        var n, i = this, l = i.config, r = lay(i.footer).find(v);
        l.range && "time" !== l.type && (t = t || l.dateTime, a = a || i.endDate, n = i.newDate(t).getTime() > i.newDate(a).getTime(), i.limit(null, t) || i.limit(null, a) ? r.addClass(s) : r[n ? "addClass" : "removeClass"](s), e && n && i.hint("string" == typeof e ? y.replace(/日期/g, e) : y))
    }, w.prototype.parse = function (e, t) {
        var a = this, n = a.config,
            i = t || (e ? lay.extend({}, a.endDate, a.endTime) : n.range ? lay.extend({}, n.dateTime, a.startTime) : n.dateTime),
            l = a.format.concat();
        return lay.each(l, function (e, t) {
            /yyyy|y/.test(t) ? l[e] = lay.digit(i.year, t.length) : /MM|M/.test(t) ? l[e] = lay.digit(i.month + 1, t.length) : /dd|d/.test(t) ? l[e] = lay.digit(i.date, t.length) : /HH|H/.test(t) ? l[e] = lay.digit(i.hours, t.length) : /mm|m/.test(t) ? l[e] = lay.digit(i.minutes, t.length) : /ss|s/.test(t) && (l[e] = lay.digit(i.seconds, t.length))
        }), n.range && !e ? l.join("") + " " + n.range + " " + a.parse(1) : l.join("")
    }, w.prototype.newDate = function (e) {
        return e = e || {}, new Date(e.year || 1, e.month || 0, e.date || 1, e.hours || 0, e.minutes || 0, e.seconds || 0)
    }, w.prototype.setValue = function (e) {
        var t = this, a = t.config, n = t.bindElem || a.elem[0], i = t.isInput(n) ? "val" : "html";
        return "static" === a.position || lay(n)[i](e || ""), this
    }, w.prototype.stampRange = function () {
        var e, t, a = this, n = a.config, i = n.dateTime, l = lay(a.elem).find("td");
        if (n.range && !a.endDate && lay(a.footer).find(v).addClass(s), a.endDate) return e = a.newDate({
            year: i.year,
            month: i.month,
            date: i.date
        }).getTime(), t = a.newDate({
            year: a.endDate.year,
            month: a.endDate.month,
            date: a.endDate.date
        }).getTime(), e > t ? a.hint(y) : void lay.each(l, function (n, i) {
            var l = lay(i).attr("lay-ymd").split("-"),
                r = a.newDate({year: l[0], month: l[1] - 1, date: l[2]}).getTime();
            lay(i).removeClass(u + " " + o), r !== e && r !== t || lay(i).addClass(lay(i).hasClass(f) || lay(i).hasClass(p) ? u : o), r > e && r < t && lay(i).addClass(u)
        })
    }, w.prototype.done = function (e, t) {
        var a = this, n = a.config, i = lay.extend({}, lay.extend(n.dateTime, a.startTime)),
            l = lay.extend({}, lay.extend(a.endDate, a.endTime));
        return lay.each([i, l], function (e, t) {
            "month" in t && lay.extend(t, {month: t.month + 1})
        }), e = e || [a.parse(), i, l], "function" == typeof n[t || "done"] && n[t || "done"].apply(n, e), a
    }, w.prototype.choose = function (e, t) {
        var a = this, n = a.config, i = a.thisDateTime(t), l = (lay(a.elem).find("td"), e.attr("lay-ymd").split("-"));
        l = {
            year: 0 | l[0],
            month: (0 | l[1]) - 1,
            date: 0 | l[2]
        }, e.hasClass(s) || (lay.extend(i, l), n.range ? (lay.each(["startTime", "endTime"], function (e, t) {
            a[t] = a[t] || {hours: 0, minutes: 0, seconds: 0}
        }), a.calendar(null, t)) : "static" === n.position ? a.calendar().done().done(null, "change") : "date" === n.type ? a.setValue(a.parse()).remove().done() : "datetime" === n.type && a.calendar().done(null, "change"))
    }, w.prototype.tool = function (e, t) {
        var a = this, n = a.config, i = n.dateTime, l = "static" === n.position, r = {
            datetime: function () {
                lay(e).hasClass(s) || (a.list("time", 0), n.range && a.list("time", 1), lay(e).attr("lay-type", "date").html(a.lang().dateTips))
            }, date: function () {
                a.closeList(), lay(e).attr("lay-type", "datetime").html(a.lang().timeTips)
            }, clear: function () {
                a.setValue("").remove(), l && (lay.extend(i, a.firstDate), a.calendar()), n.range && (delete a.endDate, delete a.startTime, delete a.endTime), a.done(["", {}, {}])
            }, now: function () {
                var e = new Date;
                lay.extend(i, a.systemDate(), {
                    hours: e.getHours(),
                    minutes: e.getMinutes(),
                    seconds: e.getSeconds()
                }), a.setValue(a.parse()).remove(), l && a.calendar(), a.done()
            }, confirm: function () {
                if (n.range) {
                    if (!a.endDate) return a.hint("请先选择日期范围");
                    if (lay(e).hasClass(s)) return a.hint("time" === n.type ? y.replace(/日期/g, "时间") : y)
                } else if (lay(e).hasClass(s)) return a.hint("不在有效日期或时间范围内");
                a.done(), a.setValue(a.parse()).remove()
            }
        };
        r[t] && r[t]()
    }, w.prototype.change = function (e) {
        var t = this, a = t.config, n = t.thisDateTime(e), i = a.range && ("year" === a.type || "month" === a.type),
            l = t.elemCont[e || 0], r = t.listYM[e], o = function (o) {
                var s = lay(l).find(".laydate-year-list")[0], y = lay(l).find(".laydate-month-list")[0];
                return s && (r[0] = o ? r[0] - 15 : r[0] + 15, t.list("year", e)), y && (o ? r[0]-- : r[0]++, t.list("month", e)), (s || y) && (lay.extend(n, {year: r[0]}), i && (n.year = r[0]), a.range || t.done(null, "change"), a.range || t.limit(lay(t.footer).find(v), {year: r[0]})), t.setBtnStatus(), s || y
            };
        return {
            prevYear: function () {
                o("sub") || (n.year--, t.checkDate("limit").calendar(null, e), a.range || t.done(null, "change"))
            }, prevMonth: function () {
                var i = t.getAsYM(n.year, n.month, "sub");
                lay.extend(n, {
                    year: i[0],
                    month: i[1]
                }), t.checkDate("limit").calendar(null, e), a.range || t.done(null, "change")
            }, nextMonth: function () {
                var i = t.getAsYM(n.year, n.month);
                lay.extend(n, {
                    year: i[0],
                    month: i[1]
                }), t.checkDate("limit").calendar(null, e), a.range || t.done(null, "change")
            }, nextYear: function () {
                o() || (n.year++, t.checkDate("limit").calendar(null, e), a.range || t.done(null, "change"))
            }
        }
    }, w.prototype.changeEvent = function () {
        var e = this;
        e.config;
        lay(e.elem).on("click", function (e) {
            lay.stope(e)
        }), lay.each(e.elemHeader, function (t, a) {
            lay(a[0]).on("click", function (a) {
                e.change(t).prevYear()
            }), lay(a[1]).on("click", function (a) {
                e.change(t).prevMonth()
            }), lay(a[2]).find("span").on("click", function (a) {
                var n = lay(this), i = n.attr("lay-ym"), l = n.attr("lay-type");
                i && (i = i.split("-"), e.listYM[t] = [0 | i[0], 0 | i[1]], e.list(l, t), lay(e.footer).find(D).addClass(s))
            }), lay(a[3]).on("click", function (a) {
                e.change(t).nextMonth()
            }), lay(a[4]).on("click", function (a) {
                e.change(t).nextYear()
            })
        }), lay.each(e.table, function (t, a) {
            var n = lay(a).find("td");
            n.on("click", function () {
                e.choose(lay(this), t)
            })
        }), lay(e.footer).find("span").on("click", function () {
            var t = lay(this).attr("lay-type");
            e.tool(this, t)
        })
    }, w.prototype.isInput = function (e) {
        return /input|textarea/.test(e.tagName.toLocaleLowerCase())
    }, w.prototype.events = function () {
        var t = this, a = t.config, n = function (e, n) {
            e.on(a.trigger, function () {
                n && (t.bindElem = this), t.render()
            })
        };
        a.elem[0] && !a.elem[0].eventHandler && (n(a.elem, "bind"), n(a.eventElem), lay(document).on("click", function (e) {
            e.target !== a.elem[0] && e.target !== a.eventElem[0] && e.target !== lay(a.closeStop)[0] && t.remove()
        }).on("keydown", function (e) {
            13 === e.keyCode && lay("#" + t.elemID)[0] && t.elemID === w.thisElem && (e.preventDefault(), lay(t.footer).find(v)[0].click())
        }), lay(e).on("resize", function () {
            return !(!t.elem || !lay(r)[0]) && void t.position()
        }), a.elem[0].eventHandler = !0)
    }, n.render = function (e) {
        var t = new w(e);
        return i.call(t)
    }, n.getEndDate = function (e, t) {
        var a = new Date;
        return a.setFullYear(t || a.getFullYear(), e || a.getMonth() + 1, 1), new Date(a.getTime() - 864e5).getDate()
    }, t ? (n.ready(), layui.define("lay", function (e) {
        n.path = layui.cache.dir, e(l, n)
    })) : "function" == typeof define && define.amd ? define(function () {
        return n
    }) : function () {
        n.ready(), e.laydate = n
    }()
}(window);