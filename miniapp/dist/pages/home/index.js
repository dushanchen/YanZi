'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

var _lessonItem = require('./../../components/home/lessonItem.js');

var _lessonItem2 = _interopRequireDefault(_lessonItem);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var HomeIndex = function (_wepy$page) {
  _inherits(HomeIndex, _wepy$page);

  function HomeIndex() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, HomeIndex);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = HomeIndex.__proto__ || Object.getPrototypeOf(HomeIndex)).call.apply(_ref, [this].concat(args))), _this), _this.$repeat = { "lessons": { "com": "LessonItem", "props": "item.sync" } }, _this.$props = { "LessonItem": { "v-bind:item.sync": { "value": "item", "type": "item", "for": "lessons", "item": "item", "index": "index", "key": "index" } } }, _this.$events = {}, _this.components = {
      LessonItem: _lessonItem2.default
    }, _this.data = {
      url: {
        store_img: 'http://qiniu.image.yetter.cn/mini-home-store.png',
        store_button: 'http://qiniu.image.yetter.cn/mini-home-store-button.png',
        shalou: 'http://qiniu.image.yetter.cn/mini-shalou.jpg',
        medal: 'http://qiniu.image.yetter.cn/mini-medal.jpg'
      },
      userInfo: {},
      first: false,
      windowH: 0,
      menuStatus: [false, false, false, false],
      lessons: [],
      courses: [{ id: 1, title: '节目名称', location: '千寻寿司(新天地店)', distance: '1.2km' }, { id: 2, title: '节目名称', location: '新元素(新天地店)', distance: '3.2km' }]
    }, _this.methods = {
      handleClickCourse: function handleClickCourse(index) {
        console.log(index);
        if (index + 1 === this.courses.length) {
          _wepy2.default.switchTab({
            url: '/pages/discovery/index'
          });
        }
      }
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(HomeIndex, [{
    key: 'onLoad',
    value: function onLoad() {
      this.windowH = this.$parent.globalData.windowH;
      this.getUserInfo();
      this.getLessons();
    }
  }, {
    key: 'getCourses',
    value: function getCourses() {
      var _this2 = this;

      var url = 'http://47.96.6.111:8080/pisces/user/load/courses?token=';
      var token = this.$parent.globalData.token;
      _wepy2.default.request(url + token).then(function (r) {
        console.dir(r);
        _this2.courses = r.data.message.courseInfos;
        _this2.courses.push({
          image: '',
          title: ''
        });
        _this2.$apply();
      });
    }
  }, {
    key: 'getUserInfo',
    value: function getUserInfo() {
      var _this3 = this;

      _wepy2.default.getUserInfo().then(function (d) {
        _this3.userInfo = d.userInfo;
        _this3.$apply();
      });
    }
  }, {
    key: 'processStartTime',
    value: function processStartTime(d) {
      var date = new Date(d);
      var M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '月';
      var D = date.getDate() + '日';
      return M + D;
    }
  }, {
    key: 'getLessons',
    value: function getLessons() {
      var _this4 = this;

      _wepy2.default.request({
        url: this.$parent.globalData.domain + 'pisces/user/load/lessons',
        data: {
          token: this.$parent.globalData.token,
          courseId: 1
        }
      }).then(function (r) {
        _this4.lessons = r.data.message.lessonInfos;
        _this4.lessons.forEach(function (element) {
          element.termLesson.startTimeCn = _this4.processStartTime(element.termLesson.startTime);
        });
      });
    }
  }, {
    key: 'clickMain',
    value: function clickMain() {
      this.getCourses();
      for (var i = 0; i <= 3; i++) {
        if (i === 0) {
          this.menuStatus[0] = !this.menuStatus[0];
        } else {
          this.menuStatus[i] = false;
        }
      }
    }
  }, {
    key: 'clickMenuOne',
    value: function clickMenuOne() {
      for (var i = 0; i <= 3; i++) {
        if (i === 1) {
          this.menuStatus[1] = !this.menuStatus[1];
        } else {
          this.menuStatus[i] = false;
        }
      }
    }
  }, {
    key: 'clickMenuTwo',
    value: function clickMenuTwo() {
      for (var i = 0; i <= 3; i++) {
        if (i === 2) {
          this.menuStatus[2] = !this.menuStatus[2];
        } else {
          this.menuStatus[i] = false;
        }
      }
    }
  }, {
    key: 'clickMenuThree',
    value: function clickMenuThree() {
      for (var i = 0; i <= 3; i++) {
        if (i === 3) {
          this.menuStatus[3] = !this.menuStatus[3];
        } else {
          this.menuStatus[i] = false;
        }
      }
    }
  }, {
    key: 'scroll',
    value: function scroll(e) {
      for (var i = 0; i <= 3; i++) {
        this.menuStatus[i] = false;
      }
    }
  }, {
    key: 'goLesson',
    value: function goLesson(e) {
      // console.log(e)
    }
  }]);

  return HomeIndex;
}(_wepy2.default.page);


Page(require('./../../npm/wepy/lib/wepy.js').default.$createPage(HomeIndex , 'pages/home/index'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImluZGV4LmpzIl0sIm5hbWVzIjpbIkhvbWVJbmRleCIsIiRyZXBlYXQiLCIkcHJvcHMiLCIkZXZlbnRzIiwiY29tcG9uZW50cyIsIkxlc3Nvbkl0ZW0iLCJkYXRhIiwidXJsIiwic3RvcmVfaW1nIiwic3RvcmVfYnV0dG9uIiwic2hhbG91IiwibWVkYWwiLCJ1c2VySW5mbyIsImZpcnN0Iiwid2luZG93SCIsIm1lbnVTdGF0dXMiLCJsZXNzb25zIiwiY291cnNlcyIsImlkIiwidGl0bGUiLCJsb2NhdGlvbiIsImRpc3RhbmNlIiwibWV0aG9kcyIsImhhbmRsZUNsaWNrQ291cnNlIiwiaW5kZXgiLCJjb25zb2xlIiwibG9nIiwibGVuZ3RoIiwic3dpdGNoVGFiIiwiJHBhcmVudCIsImdsb2JhbERhdGEiLCJnZXRVc2VySW5mbyIsImdldExlc3NvbnMiLCJ0b2tlbiIsInJlcXVlc3QiLCJ0aGVuIiwiciIsImRpciIsIm1lc3NhZ2UiLCJjb3Vyc2VJbmZvcyIsInB1c2giLCJpbWFnZSIsIiRhcHBseSIsImQiLCJkYXRlIiwiRGF0ZSIsIk0iLCJnZXRNb250aCIsIkQiLCJnZXREYXRlIiwiZG9tYWluIiwiY291cnNlSWQiLCJsZXNzb25JbmZvcyIsImZvckVhY2giLCJlbGVtZW50IiwidGVybUxlc3NvbiIsInN0YXJ0VGltZUNuIiwicHJvY2Vzc1N0YXJ0VGltZSIsInN0YXJ0VGltZSIsImdldENvdXJzZXMiLCJpIiwiZSIsInBhZ2UiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7OztBQUNBOzs7O0FBQ0E7Ozs7Ozs7Ozs7OztJQUNxQkEsUzs7Ozs7Ozs7Ozs7Ozs7NExBQ3BCQyxPLEdBQVUsRUFBQyxXQUFVLEVBQUMsT0FBTSxZQUFQLEVBQW9CLFNBQVEsV0FBNUIsRUFBWCxFLFFBQ1hDLE0sR0FBUyxFQUFDLGNBQWEsRUFBQyxvQkFBbUIsRUFBQyxTQUFRLE1BQVQsRUFBZ0IsUUFBTyxNQUF2QixFQUE4QixPQUFNLFNBQXBDLEVBQThDLFFBQU8sTUFBckQsRUFBNEQs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QUNGO0FBQ0Y7OztxQ0FDZ0I7QUFDZixXQUFLLElBQUlBLElBQUksQ0FBYixFQUFnQkEsS0FBSyxDQUFyQixFQUF3QkEsR0FBeEIsRUFBNkI7QUFDM0IsWUFBSUEsTUFBTSxDQUFWLEVBQWE7QUFDWCxlQUFLN0MsVUFBTCxDQUFnQixDQUFoQixJQUFxQixDQUFDLEtBQUtBLFVBQUwsQ0FBZ0IsQ0FBaEIsQ0FBdEI7QUFDRCxTQUZELE1BRU87QUFDTCxlQUFLQSxVQUFMLENBQWdCNkMsQ0FBaEIsSUFBcUIsS0FBckI7QUFDRDtBQUNGO0FBQ0Y7OzsyQkFDTUMsQyxFQUFHO0FBQ1IsV0FBSyxJQUFJRCxJQUFJLENBQWIsRUFBZ0JBLEtBQUssQ0FBckIsRUFBd0JBLEdBQXhCLEVBQTZCO0FBQzNCLGFBQUs3QyxVQUFMLENBQWdCNkMsQ0FBaEIsSUFBcUIsS0FBckI7QUFDRDtBQUNGOzs7NkJBQ1FDLEMsRUFBRztBQUNWO0FBQ0Q7Ozs7RUEvSG9DLGVBQUtDLEk7O2tCQUF2QjlELFMiLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VzQ29udGVudCI6WyJcbmltcG9ydCB3ZXB5IGZyb20gJ3dlcHknXG5pbXBvcnQgTGVzc29uSXRlbSBmcm9tICcuLi8uLi9jb21wb25lbnRzL2hvbWUvbGVzc29uSXRlbSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIEhvbWVJbmRleCBleHRlbmRzIHdlcHkucGFnZSB7XG4gJHJlcGVhdCA9IHtcImxlc3NvbnNcIjp7XCJjb21cIjpcIkxlc3Nvbkl0ZW1cIixcInByb3BzXCI6XCJpdGVtLnN5bmNcIn19O1xyXG4kcHJvcHMgPSB7XCJMZXNzb25JdGVtXCI6e1widi1iaW5kOml0ZW0uc3luY1wiOntcInZhbHVlXCI6XCJpdGVtXCIsXCJ0eXBlXCI6XCJpdGVtXCIsXCJmb3JcIjpcImxlc3NvbnNcIixcIml0ZW1cIjpcIml0ZW1cIixcImluZGV4XCI6XCJpbmRleFwiLFwia2V5XCI6XCJpbmRleFwifX19O1xyXG4kZXZlbnRzID0ge307XHJcbiBjb21wb25lbnRzID0ge1xuICAgIExlc3Nvbkl0ZW06IExlc3Nvbkl0ZW1cbiAgfVxuICBkYXRhID0ge1xuICAgIHVybDoge1xuICAgICAgc3RvcmVfaW1nOiAnaHR0cDovL3Fpbml1LmltYWdlLnlldHRlci5jbi9taW5pLWhvbWUtc3RvcmUucG5nJyxcbiAgICAgIHN0b3JlX2J1dHRvbjogJ2h0dHA6Ly9xaW5pdS5pbWFnZS55ZXR0ZXIuY24vbWluaS1ob21lLXN0b3JlLWJ1dHRvbi5wbmcnLFxuICAgICAgc2hhbG91OiAnaHR0cDovL3Fpbml1LmltYWdlLnlldHRlci5jbi9taW5pLXNoYWxvdS5qcGcnLFxuICAgICAgbWVkYWw6ICdodHRwOi8vcWluaXUuaW1hZ2UueWV0dGVyLmNuL21pbmktbWVkYWwuanBnJ1xuICAgIH0sXG4gICAgdXNlckluZm86IHt9LFxuICAgIGZpcnN0OiBmYWxzZSxcbiAgICB3aW5kb3dIOiAwLFxuICAgIG1lbnVTdGF0dXM6IFtcbiAgICAgIGZhbHNlLFxuICAgICAgZmFsc2UsXG4gICAgICBmYWxzZSxcbiAgICAgIGZhbHNlXG4gICAgXSxcbiAgICBsZXNzb25zOiBbXSxcbiAgICBjb3Vyc2VzOiBbXG4gICAgICB7aWQ6IDEsIHRpdGxlOiAn6IqC55uu5ZCN56ewJywgbG9jYXRpb246ICfljYPlr7vlr7/lj7go5paw5aSp5Zyw5bqXKScsIGRpc3RhbmNlOiAnMS4ya20nfSxcbiAgICAgIHtpZDogMiwgdGl0bGU6ICfoioLnm67lkI3np7AnLCBsb2NhdGlvbjogJ+aWsOWFg+e0oCjmlrDlpKnlnLDlupcpJywgZGlzdGFuY2U6ICczLjJrbSd9XG4gICAgXVxuICB9XG4gIG1ldGhvZHMgPSB7XG4gICAgaGFuZGxlQ2xpY2tDb3Vyc2UoaW5kZXgpIHtcbiAgICAgIGNvbnNvbGUubG9nKGluZGV4KVxuICAgICAgaWYgKGluZGV4ICsgMSA9PT0gdGhpcy5jb3Vyc2VzLmxlbmd0aCkge1xuICAgICAgICB3ZXB5LnN3aXRjaFRhYih7XG4gICAgICAgICAgdXJsOiAnL3BhZ2VzL2Rpc2NvdmVyeS9pbmRleCdcbiAgICAgICAgfSlcbiAgICAgIH1cbiAgICB9XG4gIH1cbiAgb25Mb2FkKCkge1xuICAgIHRoaXMud2luZG93SCA9IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLndpbmRvd0hcbiAgICB0aGlzLmdldFVzZXJJbmZvKClcbiAgICB0aGlzLmdldExlc3NvbnMoKVxuICB9XG4gIGdldENvdXJzZXMoKSB7XG4gICAgbGV0IHVybCA9ICdodHRwOi8vNDcuOTYuNi4xMTE6ODA4MC9waXNjZXMvdXNlci9sb2FkL2NvdXJzZXM/dG9rZW49J1xuICAgIGxldCB0b2tlbiA9IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLnRva2VuXG4gICAgd2VweS5yZXF1ZXN0KHVybCArIHRva2VuKS50aGVuKChyKSA9PiB7XG4gICAgICBjb25zb2xlLmRpcihyKVxuICAgICAgdGhpcy5jb3Vyc2VzID0gci5kYXRhLm1lc3NhZ2UuY291cnNlSW5mb3NcbiAgICAgIHRoaXMuY291cnNlcy5wdXNoKHtcbiAgICAgICAgaW1hZ2U6ICcnLFxuICAgICAgICB0aXRsZTogJydcbiAgICAgIH0pXG4gICAgICB0aGlzLiRhcHBseSgpXG4gICAgfSlcbiAgfVxuICBnZXRVc2VySW5mbygpIHtcbiAgICB3ZXB5LmdldFVzZXJJbmZvKCkudGhlbiggKGQpID0+IHtcbiAgICAgIHRoaXMudXNlckluZm8gPSBkLnVzZXJJbmZvXG4gICAgICB0aGlzLiRhcHBseSgpXG4gICAgfSlcbiAgfVxuICBwcm9jZXNzU3RhcnRUaW1lIChkKSB7XG4gICAgbGV0IGRhdGUgPSBuZXcgRGF0ZShkKVxuICAgIGxldCBNID0gKGRhdGUuZ2V0TW9udGgoKSsxIDwgMTAgPyAnMCcrKGRhdGUuZ2V0TW9udGgoKSsxKSA6IGRhdGUuZ2V0TW9udGgoKSsxKSArICfmnIgnXG4gICAgbGV0IEQgPSBkYXRlLmdldERhdGUoKSArICfml6UnXG4gICAgcmV0dXJuIE0gKyBEXG4gIH1cbiAgZ2V0TGVzc29ucygpIHtcbiAgICB3ZXB5LnJlcXVlc3Qoe1xuICAgICAgdXJsOiB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS5kb21haW4gKyAncGlzY2VzL3VzZXIvbG9hZC9sZXNzb25zJyxcbiAgICAgIGRhdGE6IHtcbiAgICAgICAgdG9rZW46IHRoaXMuJHBhcmVudC5nbG9iYWxEYXRhLnRva2VuLFxuICAgICAgICBjb3Vyc2VJZDogMVxuICAgICAgfVxuICAgIH0pLnRoZW4oKHIpID0+IHtcbiAgICAgIHRoaXMubGVzc29ucyA9IHIuZGF0YS5tZXNzYWdlLmxlc3NvbkluZm9zXG4gICAgICB0aGlzLmxlc3NvbnMuZm9yRWFjaChlbGVtZW50ID0+IHtcbiAgICAgICAgZWxlbWVudC50ZXJtTGVzc29uLnN0YXJ0VGltZUNuID0gdGhpcy5wcm9jZXNzU3RhcnRUaW1lKGVsZW1lbnQudGVybUxlc3Nvbi5zdGFydFRpbWUpXG4gICAgICB9KTtcbiAgICB9KVxuICB9XG4gIGNsaWNrTWFpbigpIHtcbiAgICB0aGlzLmdldENvdXJzZXMoKVxuICAgIGZvciAobGV0IGkgPSAwOyBpIDw9IDM7IGkrKykge1xuICAgICAgaWYgKGkgPT09IDApIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzWzBdID0gIXRoaXMubWVudVN0YXR1c1swXVxuICAgICAgfSBlbHNlIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzW2ldID0gZmFsc2VcbiAgICAgIH1cbiAgICB9XG4gIH1cbiAgY2xpY2tNZW51T25lKCkge1xuICAgIGZvciAobGV0IGkgPSAwOyBpIDw9IDM7IGkrKykge1xuICAgICAgaWYgKGkgPT09IDEpIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzWzFdID0gIXRoaXMubWVudVN0YXR1c1sxXVxuICAgICAgfSBlbHNlIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzW2ldID0gZmFsc2VcbiAgICAgIH1cbiAgICB9XG4gIH1cbiAgY2xpY2tNZW51VHdvKCkge1xuICAgIGZvciAobGV0IGkgPSAwOyBpIDw9IDM7IGkrKykge1xuICAgICAgaWYgKGkgPT09IDIpIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzWzJdID0gIXRoaXMubWVudVN0YXR1c1syXVxuICAgICAgfSBlbHNlIHtcbiAgICAgICAgdGhpcy5tZW51U3RhdHVzW2ldID0gZmFsc2VcbiAgICAgIH1cbiAgICB9XG4gIH1cbiAgY2xpY2tNZW51VGhyZWUoKSB7XG4gICAgZm9yIChsZXQgaSA9IDA7IGkgPD0gMzsgaSsrKSB7XG4gICAgICBpZiAoaSA9PT0gMykge1xuICAgICAgICB0aGlzLm1lbnVTdGF0dXNbM10gPSAhdGhpcy5tZW51U3RhdHVzWzNdXG4gICAgICB9IGVsc2Uge1xuICAgICAgICB0aGlzLm1lbnVTdGF0dXNbaV0gPSBmYWxzZVxuICAgICAgfVxuICAgIH1cbiAgfVxuICBzY3JvbGwoZSkge1xuICAgIGZvciAobGV0IGkgPSAwOyBpIDw9IDM7IGkrKykge1xuICAgICAgdGhpcy5tZW51U3RhdHVzW2ldID0gZmFsc2VcbiAgICB9XG4gIH1cbiAgZ29MZXNzb24oZSkge1xuICAgIC8vIGNvbnNvbGUubG9nKGUpXG4gIH1cbn1cbiJdfQ==