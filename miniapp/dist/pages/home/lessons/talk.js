'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _createClass = function () { function defineProperties(target, props) { for (var i = 0; i < props.length; i++) { var descriptor = props[i]; descriptor.enumerable = descriptor.enumerable || false; descriptor.configurable = true; if ("value" in descriptor) descriptor.writable = true; Object.defineProperty(target, descriptor.key, descriptor); } } return function (Constructor, protoProps, staticProps) { if (protoProps) defineProperties(Constructor.prototype, protoProps); if (staticProps) defineProperties(Constructor, staticProps); return Constructor; }; }();

var _wepy = require('./../../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var TalkPart = function (_wepy$page) {
  _inherits(TalkPart, _wepy$page);

  function TalkPart() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, TalkPart);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = TalkPart.__proto__ || Object.getPrototypeOf(TalkPart)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
      windowH: 0
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(TalkPart, [{
    key: 'onLoad',
    value: function onLoad(options) {
      console.dir(options);
      var that = this;
      _wepy2.default.getSystemInfo({
        success: function success(res) {
          that.windowH = res.windowHeight;
        }
      });
      // this.windowH = this.$parent.globalData.windowH
      // console.log(this.windowH)
    }
  }]);

  return TalkPart;
}(_wepy2.default.page);

exports.default = TalkPart;
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbInRhbGsuanMiXSwibmFtZXMiOlsiVGFsa1BhcnQiLCJkYXRhIiwid2luZG93SCIsIm9wdGlvbnMiLCJjb25zb2xlIiwiZGlyIiwidGhhdCIsImdldFN5c3RlbUluZm8iLCJzdWNjZXNzIiwicmVzIiwid2luZG93SGVpZ2h0IiwicGFnZSJdLCJtYXBwaW5ncyI6Ijs7Ozs7Ozs7O0FBQ0E7Ozs7Ozs7Ozs7OztJQUNxQkEsUTs7Ozs7Ozs7Ozs7Ozs7MExBQ25CQyxJLEdBQU87QUFDTEMsZUFBUztBQURKLEs7Ozs7OzJCQUdBQyxPLEVBQVM7QUFDZEMsY0FBUUMsR0FBUixDQUFZRixPQUFaO0FBQ0EsVUFBSUcsT0FBTyxJQUFYO0FBQ0EscUJBQUtDLGFBQUwsQ0FBbUI7QUFDakJDLGlCQUFTLGlCQUFTQyxHQUFULEVBQWM7QUFDckJILGVBQUtKLE9BQUwsR0FBZU8sSUFBSUMsWUFBbkI7QUFDRDtBQUhnQixPQUFuQjtBQUtBO0FBQ0E7QUFDRDs7OztFQWRtQyxlQUFLQyxJOztrQkFBdEJYLFEiLCJmaWxlIjoidGFsay5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIFRhbGtQYXJ0IGV4dGVuZHMgd2VweS5wYWdlIHtcbiAgZGF0YSA9IHtcbiAgICB3aW5kb3dIOiAwXG4gIH1cbiAgb25Mb2FkKG9wdGlvbnMpIHtcbiAgICBjb25zb2xlLmRpcihvcHRpb25zKVxuICAgIGxldCB0aGF0ID0gdGhpc1xuICAgIHdlcHkuZ2V0U3lzdGVtSW5mbyh7XG4gICAgICBzdWNjZXNzOiBmdW5jdGlvbihyZXMpIHtcbiAgICAgICAgdGhhdC53aW5kb3dIID0gcmVzLndpbmRvd0hlaWdodFxuICAgICAgfVxuICAgIH0pXG4gICAgLy8gdGhpcy53aW5kb3dIID0gdGhpcy4kcGFyZW50Lmdsb2JhbERhdGEud2luZG93SFxuICAgIC8vIGNvbnNvbGUubG9nKHRoaXMud2luZG93SClcbiAgfVxufVxuIl19