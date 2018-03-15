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

var ChoosePart = function (_wepy$page) {
  _inherits(ChoosePart, _wepy$page);

  function ChoosePart() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, ChoosePart);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = ChoosePart.__proto__ || Object.getPrototypeOf(ChoosePart)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
      windowH: 0
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(ChoosePart, [{
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

  return ChoosePart;
}(_wepy2.default.page);

exports.default = ChoosePart;
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNob29zZS5qcyJdLCJuYW1lcyI6WyJDaG9vc2VQYXJ0IiwiZGF0YSIsIndpbmRvd0giLCJvcHRpb25zIiwiY29uc29sZSIsImRpciIsInRoYXQiLCJnZXRTeXN0ZW1JbmZvIiwic3VjY2VzcyIsInJlcyIsIndpbmRvd0hlaWdodCIsInBhZ2UiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7OztBQUNBOzs7Ozs7Ozs7Ozs7SUFDcUJBLFU7Ozs7Ozs7Ozs7Ozs7OzhMQUNuQkMsSSxHQUFPO0FBQ0xDLGVBQVM7QUFESixLOzs7OzsyQkFHQUMsTyxFQUFTO0FBQ2RDLGNBQVFDLEdBQVIsQ0FBWUYsT0FBWjtBQUNBLFVBQUlHLE9BQU8sSUFBWDtBQUNBLHFCQUFLQyxhQUFMLENBQW1CO0FBQ2pCQyxpQkFBUyxpQkFBU0MsR0FBVCxFQUFjO0FBQ3JCSCxlQUFLSixPQUFMLEdBQWVPLElBQUlDLFlBQW5CO0FBQ0Q7QUFIZ0IsT0FBbkI7QUFLQTtBQUNBO0FBQ0Q7Ozs7RUFkcUMsZUFBS0MsSTs7a0JBQXhCWCxVIiwiZmlsZSI6ImNob29zZS5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIENob29zZVBhcnQgZXh0ZW5kcyB3ZXB5LnBhZ2Uge1xuICBkYXRhID0ge1xuICAgIHdpbmRvd0g6IDBcbiAgfVxuICBvbkxvYWQob3B0aW9ucykge1xuICAgIGNvbnNvbGUuZGlyKG9wdGlvbnMpXG4gICAgbGV0IHRoYXQgPSB0aGlzXG4gICAgd2VweS5nZXRTeXN0ZW1JbmZvKHtcbiAgICAgIHN1Y2Nlc3M6IGZ1bmN0aW9uKHJlcykge1xuICAgICAgICB0aGF0LndpbmRvd0ggPSByZXMud2luZG93SGVpZ2h0XG4gICAgICB9XG4gICAgfSlcbiAgICAvLyB0aGlzLndpbmRvd0ggPSB0aGlzLiRwYXJlbnQuZ2xvYmFsRGF0YS53aW5kb3dIXG4gICAgLy8gY29uc29sZS5sb2codGhpcy53aW5kb3dIKVxuICB9XG59XG4iXX0=