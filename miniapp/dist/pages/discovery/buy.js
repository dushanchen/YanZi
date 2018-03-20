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

var DiscoveryBug = function (_wepy$page) {
  _inherits(DiscoveryBug, _wepy$page);

  function DiscoveryBug() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, DiscoveryBug);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = DiscoveryBug.__proto__ || Object.getPrototypeOf(DiscoveryBug)).call.apply(_ref, [this].concat(args))), _this), _this.data = {
      windowH: 0
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  _createClass(DiscoveryBug, [{
    key: 'onLoad',
    value: function onLoad() {
      this.windowH = this.$parent.globalData.windowH;
    }
  }, {
    key: 'scroll',
    value: function scroll(e) {}
  }, {
    key: 'getData',
    value: function getData() {}
  }]);

  return DiscoveryBug;
}(_wepy2.default.page);


Page(require('./../../npm/wepy/lib/wepy.js').default.$createPage(DiscoveryBug , 'pages/discovery/buy'));

//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImJ1eS5qcyJdLCJuYW1lcyI6WyJEaXNjb3ZlcnlCdWciLCJkYXRhIiwid2luZG93SCIsIiRwYXJlbnQiLCJnbG9iYWxEYXRhIiwiZSIsInBhZ2UiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7OztBQUNBOzs7Ozs7Ozs7Ozs7SUFDcUJBLFk7Ozs7Ozs7Ozs7Ozs7O2tNQUNuQkMsSSxHQUFPO0FBQ0xDLGVBQVM7QUFESixLOzs7Ozs2QkFHRTtBQUNQLFdBQUtBLE9BQUwsR0FBZSxLQUFLQyxPQUFMLENBQWFDLFVBQWIsQ0FBd0JGLE9BQXZDO0FBQ0Q7OzsyQkFDTUcsQyxFQUFHLENBQ1Q7Ozs4QkFDUyxDQUVUOzs7O0VBWHVDLGVBQUtDLEk7O2tCQUExQk4sWSIsImZpbGUiOiJidXkuanMiLCJzb3VyY2VzQ29udGVudCI6WyJcbmltcG9ydCB3ZXB5IGZyb20gJ3dlcHknXG5leHBvcnQgZGVmYXVsdCBjbGFzcyBEaXNjb3ZlcnlCdWcgZXh0ZW5kcyB3ZXB5LnBhZ2Uge1xuICBkYXRhID0ge1xuICAgIHdpbmRvd0g6IDBcbiAgfVxuICBvbkxvYWQoKSB7XG4gICAgdGhpcy53aW5kb3dIID0gdGhpcy4kcGFyZW50Lmdsb2JhbERhdGEud2luZG93SFxuICB9XG4gIHNjcm9sbChlKSB7XG4gIH1cbiAgZ2V0RGF0YSgpIHtcblxuICB9XG59XG4iXX0=