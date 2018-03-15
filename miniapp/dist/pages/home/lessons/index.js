'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var LessonsIndex = function (_wepy$page) {
  _inherits(LessonsIndex, _wepy$page);

  function LessonsIndex() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, LessonsIndex);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = LessonsIndex.__proto__ || Object.getPrototypeOf(LessonsIndex)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
      windowH: 0,
      options: {},
      info: {},
      loading: true
    }, _this.computed = {
      percent: function percent() {
        if (this.loading === false) {
          return this.info.userLessonStatus.correctPercent * 100;
        }
      }
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(LessonsIndex, [{
    key: 'onLoad',
    value: function onLoad(options) {
      this.windowH = this.$parent.globalData.windowH;
      this.options = options;
      this.getLessonInfo();
    }
  }, {
    key: 'goFill',
    value: function goFill() {
      _wepy2.default.redirectTo({
        url: '/pages/home/lessons/match'
      });
    }
  }, {
    key: 'getLessonInfo',
    value: function getLessonInfo() {
      var _this2 = this;

      var url = 'pisces/user/load/lessoninfo';
      _wepy2.default.request({
        url: this.$parent.globalData.domain + url,
        data: {
          token: this.$parent.globalData.token,
          courseId: this.options.courseId,
          lessonId: this.options.lessonId
        }
      }).then(function (r) {
        console.dir(r);
        _this2.info = r.data.message.lessonInfo;
        _this2.loading = false;
        _this2.$apply();
      });
    }
  }]);

  return LessonsIndex;
}(_wepy2.default.page);


Page(require('./../../../npm/wepy/lib/wepy.js').default.$createPage(LessonsIndex , 'pages/home/lessons/index'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImluZGV4LmpzIl0sIm5hbWVzIjpbIkxlc3NvbnNJbmRleCIsImRhdGEiLCJ3aW5kb3dIIiwib3B0aW9ucyIsImluZm8iLCJsb2FkaW5nIiwiY29tcHV0ZWQiLCJwZXJjZW50IiwidXNlckxlc3NvblN0YXR1cyIsImNvcnJlY3RQZXJjZW50IiwiJHBhcmVudCIsImdsb2JhbERhdGEiLCJnZXRMZXNzb25JbmZvIiwicmVkaXJlY3RUbyIsInVybCIsInJlcXVlc3QiLCJkb21haW4iLCJ0b2tlbiIsImNvdXJzZUlkIiwibGVzc29uSWQiLCJ0aGVuIiwiciIsImNvbnNvbGUiLCJkaXIiLCJtZXNzYWdlIiwibGVzc29uSW5mbyIsIiRhcHBseSIsInBhZ2UiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7OztBQUNBOzs7Ozs7Ozs7Ozs7SUFDcUJBLFk7Ozs7Ozs7Ozs7Ozs7O2tNQUNuQkMsSSxHQUFPO0FBQ0xDLGVBQVMsQ0FESjtBQUVMQyxlQUFTLEVBRko7QUFHTEMsWUFBTSxFQUhEO0FBSUxDLGVBQVM7QUFKSixLLFFBV1BDLFEsR0FBVztBQUNUQyxhQURTLHFCQUNDO0FBQ1IsWUFBSSxLQUFLRixPQUFMLEtBQWlCLEtBQXJCLEVBQTRCO0FBQzFCLGlCQUFPLEtBQUtELElBQUwsQ0FBVUksZ0JBQVYsQ0FBMkJDLGNBQTNCLEdBQTRDLEdBQW5EO0FBQ0Q7QUFDRjtBQUxRLEs7Ozs7OzJCQUxKTixPLEVBQVM7QUFDZCxXQUFLRCxPQUFMLEdBQWUsS0FBS1EsT0FBTCxDQUFhQyxVQUFiLENBQXdCVCxPQUF2QztBQUNBLFdBQUtDLE9BQUwsR0FBZUEsT0FBZjtBQUNBLFdBQUtTLGFBQUw7QUFDRDs7OzZCQVFRO0FBQ1AscUJBQUtDLFVBQUwsQ0FBZ0I7QUFDZEMsYUFBSztBQURTLE9BQWhCO0FBR0Q7OztvQ0FDZTtBQUFBOztBQUNkLFVBQUlBLE1BQU0sNkJBQVY7QUFDQSxxQkFBS0MsT0FBTCxDQUFhO0FBQ1hELGFBQUssS0FBS0osT0FBTCxDQUFhQyxVQUFiLENBQXdCSyxNQUF4QixHQUFpQ0YsR0FEM0I7QUFFWGIsY0FBTTtBQUNKZ0IsaUJBQU8sS0FBS1AsT0FBTCxDQUFhQyxVQUFiLENBQXdCTSxLQUQzQjtBQUVKQyxvQkFBVSxLQUFLZixPQUFMLENBQWFlLFFBRm5CO0FBR0pDLG9CQUFVLEtBQUtoQixPQUFMLENBQWFnQjtBQUhuQjtBQUZLLE9BQWIsRUFPR0MsSUFQSCxDQU9RLFVBQUNDLENBQUQsRUFBTztBQUNiQyxnQkFBUUMsR0FBUixDQUFZRixDQUFaO0FBQ0EsZUFBS2pCLElBQUwsR0FBWWlCLEVBQUVwQixJQUFGLENBQU91QixPQUFQLENBQWVDLFVBQTNCO0FBQ0EsZUFBS3BCLE9BQUwsR0FBZSxLQUFmO0FBQ0EsZUFBS3FCLE1BQUw7QUFDRCxPQVpEO0FBYUQ7Ozs7RUF2Q3VDLGVBQUtDLEk7O2tCQUExQjNCLFkiLCJmaWxlIjoiaW5kZXguanMiLCJzb3VyY2VzQ29udGVudCI6WyJcbmltcG9ydCB3ZXB5IGZyb20gJ3dlcHknXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBMZXNzb25zSW5kZXggZXh0ZW5kcyB3ZXB5LnBhZ2Uge1xuICBkYXRhID0ge1xuICAgIHdpbmRvd0g6IDAsXG4gICAgb3B0aW9uczoge30sXG4gICAgaW5mbzoge30sXG4gICAgbG9hZGluZzogdHJ1ZVxuICB9XG4gIG9uTG9hZChvcHRpb25zKSB7XG4gICAgdGhpcy53aW5kb3dIID0gdGhpcy4kcGFyZW50Lmdsb2JhbERhdGEud2luZG93SFxuICAgIHRoaXMub3B0aW9ucyA9IG9wdGlvbnNcbiAgICB0aGlzLmdldExlc3NvbkluZm8oKVxuICB9XG4gIGNvbXB1dGVkID0ge1xuICAgIHBlcmNlbnQoKSB7XG4gICAgICBpZiAodGhpcy5sb2FkaW5nID09PSBmYWxzZSkge1xuICAgICAgICByZXR1cm4gdGhpcy5pbmZvLnVzZXJMZXNzb25TdGF0dXMuY29ycmVjdFBlcmNlbnQgKiAxMDBcbiAgICAgIH1cbiAgICB9XG4gIH1cbiAgZ29GaWxsKCkge1xuICAgIHdlcHkucmVkaXJlY3RUbyh7XG4gICAgICB1cmw6ICcvcGFnZXMvaG9tZS9sZXNzb25zL21hdGNoJ1xuICAgIH0pXG4gIH1cbiAgZ2V0TGVzc29uSW5mbygpIHtcbiAgICBsZXQgdXJsID0gJ3Bpc2Nlcy91c2VyL2xvYWQvbGVzc29uaW5mbydcbiAgICB3ZXB5LnJlcXVlc3Qoe1xuICAgICAgdXJsOiB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS5kb21haW4gKyB1cmwsXG4gICAgICBkYXRhOiB7XG4gICAgICAgIHRva2VuOiB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS50b2tlbixcbiAgICAgICAgY291cnNlSWQ6IHRoaXMub3B0aW9ucy5jb3Vyc2VJZCxcbiAgICAgICAgbGVzc29uSWQ6IHRoaXMub3B0aW9ucy5sZXNzb25JZFxuICAgICAgfVxuICAgIH0pLnRoZW4oKHIpID0+IHtcbiAgICAgIGNvbnNvbGUuZGlyKHIpXG4gICAgICB0aGlzLmluZm8gPSByLmRhdGEubWVzc2FnZS5sZXNzb25JbmZvXG4gICAgICB0aGlzLmxvYWRpbmcgPSBmYWxzZVxuICAgICAgdGhpcy4kYXBwbHkoKVxuICAgIH0pXG4gIH1cbn1cbiJdfQ==