'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});


var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var AccountIndex = function (_wepy$page) {
  _inherits(AccountIndex, _wepy$page);

  function AccountIndex() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, AccountIndex);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = AccountIndex.__proto__ || Object.getPrototypeOf(AccountIndex)).call.apply(_ref, [this].concat(args))), _this), _this.config = {
      backgroundTextStyle: 'light',
      navigationBarBackgroundColor: '#fff',
      navigationBarTitleText: '我的',
      navigationBarTextStyle: 'black'
    }, _this.data = {
      userInfo: {
        nickName: '加载中...'
      },
      bgUrl: 'http://qiniu.image.yetter.cn/bg.png'
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(AccountIndex, [{
    key: 'onLoad',
    value: function onLoad() {
      this.userInfo = this.$parent.globalData.userInfo;
    }
  }, {
    key: 'naviToAward',
    value: function naviToAward() {
      _wepy2.default.navigateTo({
        url: 'reward'
      });
    }
  }, {
    key: 'naviToMedal',
    value: function naviToMedal() {
      _wepy2.default.navigateTo({
        url: 'medal'
      });
    }
  }, {
    key: 'naviToSetting',
    value: function naviToSetting() {
      _wepy2.default.navigateTo({
        url: 'setting'
      });
    }
  }, {
    key: 'naviToMessage',
    value: function naviToMessage() {
      _wepy2.default.navigateTo({
        url: 'message'
      });
    }
  }]);

  return AccountIndex;
}(_wepy2.default.page);


Page(require('./../../npm/wepy/lib/wepy.js').default.$createPage(AccountIndex , 'pages/account/index'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImluZGV4LmpzIl0sIm5hbWVzIjpbIkFjY291bnRJbmRleCIsImNvbmZpZyIsImJhY2tncm91bmRUZXh0U3R5bGUiLCJuYXZpZ2F0aW9uQmFyQmFja2dyb3VuZENvbG9yIiwibmF2aWdhdGlvbkJhclRpdGxlVGV4dCIsIm5hdmlnYXRpb25CYXJUZXh0U3R5bGUiLCJkYXRhIiwidXNlckluZm8iLCJuaWNrTmFtZSIsImJnVXJsIiwiJHBhcmVudCIsImdsb2JhbERhdGEiLCJuYXZpZ2F0ZVRvIiwidXJsIiwicGFnZSJdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7O0FBQ0E7Ozs7Ozs7Ozs7OztJQUNxQkEsWTs7Ozs7Ozs7Ozs7Ozs7a01BQ25CQyxNLEdBQVM7QUFDUEMsMkJBQXFCLE9BRGQ7QUFFUEMsb0NBQThCLE1BRnZCO0FBR1BDLDhCQUF3QixJQUhqQjtBQUlQQyw4QkFBd0I7QUFKakIsSyxRQU1UQyxJLEdBQU87QUFDTEMsZ0JBQVU7QUFDUkMsa0JBQVU7QUFERixPQURMO0FBSUxDLGFBQU87QUFKRixLOzs7Ozs2QkFNRTtBQUNQLFdBQUtGLFFBQUwsR0FBZ0IsS0FBS0csT0FBTCxDQUFhQyxVQUFiLENBQXdCSixRQUF4QztBQUNEOzs7a0NBQ2E7QUFDWixxQkFBS0ssVUFBTCxDQUFnQjtBQUNkQyxhQUFLO0FBRFMsT0FBaEI7QUFHRDs7O2tDQUNhO0FBQ1oscUJBQUtELFVBQUwsQ0FBZ0I7QUFDZEMsYUFBSztBQURTLE9BQWhCO0FBR0Q7OztvQ0FDZTtBQUNkLHFCQUFLRCxVQUFMLENBQWdCO0FBQ2RDLGFBQUs7QUFEUyxPQUFoQjtBQUdEOzs7b0NBQ2U7QUFDZCxxQkFBS0QsVUFBTCxDQUFnQjtBQUNkQyxhQUFLO0FBRFMsT0FBaEI7QUFHRDs7OztFQW5DdUMsZUFBS0MsSTs7a0JBQTFCZCxZIiwiZmlsZSI6ImluZGV4LmpzIiwic291cmNlc0NvbnRlbnQiOlsiXG5pbXBvcnQgd2VweSBmcm9tICd3ZXB5J1xuZXhwb3J0IGRlZmF1bHQgY2xhc3MgQWNjb3VudEluZGV4IGV4dGVuZHMgd2VweS5wYWdlIHtcbiAgY29uZmlnID0ge1xuICAgIGJhY2tncm91bmRUZXh0U3R5bGU6ICdsaWdodCcsXG4gICAgbmF2aWdhdGlvbkJhckJhY2tncm91bmRDb2xvcjogJyNmZmYnLFxuICAgIG5hdmlnYXRpb25CYXJUaXRsZVRleHQ6ICfmiJHnmoQnLFxuICAgIG5hdmlnYXRpb25CYXJUZXh0U3R5bGU6ICdibGFjaydcbiAgfVxuICBkYXRhID0ge1xuICAgIHVzZXJJbmZvOiB7XG4gICAgICBuaWNrTmFtZTogJ+WKoOi9veS4rS4uLidcbiAgICB9LFxuICAgIGJnVXJsOiAnaHR0cDovL3Fpbml1LmltYWdlLnlldHRlci5jbi9iZy5wbmcnXG4gIH1cbiAgb25Mb2FkKCkge1xuICAgIHRoaXMudXNlckluZm8gPSB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS51c2VySW5mb1xuICB9XG4gIG5hdmlUb0F3YXJkKCkge1xuICAgIHdlcHkubmF2aWdhdGVUbyh7XG4gICAgICB1cmw6ICdyZXdhcmQnXG4gICAgfSlcbiAgfVxuICBuYXZpVG9NZWRhbCgpIHtcbiAgICB3ZXB5Lm5hdmlnYXRlVG8oe1xuICAgICAgdXJsOiAnbWVkYWwnXG4gICAgfSlcbiAgfVxuICBuYXZpVG9TZXR0aW5nKCkge1xuICAgIHdlcHkubmF2aWdhdGVUbyh7XG4gICAgICB1cmw6ICdzZXR0aW5nJ1xuICAgIH0pXG4gIH1cbiAgbmF2aVRvTWVzc2FnZSgpIHtcbiAgICB3ZXB5Lm5hdmlnYXRlVG8oe1xuICAgICAgdXJsOiAnbWVzc2FnZSdcbiAgICB9KVxuICB9XG59XG4iXX0=