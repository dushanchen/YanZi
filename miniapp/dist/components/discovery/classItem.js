'use strict';

Object.defineProperty(exports, "__esModule", {
  value: true
});
exports.default = undefined;

var _wepy = require('./../../npm/wepy/lib/wepy.js');

var _wepy2 = _interopRequireDefault(_wepy);

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }

function _classCallCheck(instance, Constructor) { if (!(instance instanceof Constructor)) { throw new TypeError("Cannot call a class as a function"); } }

function _possibleConstructorReturn(self, call) { if (!self) { throw new ReferenceError("this hasn't been initialised - super() hasn't been called"); } return call && (typeof call === "object" || typeof call === "function") ? call : self; }

function _inherits(subClass, superClass) { if (typeof superClass !== "function" && superClass !== null) { throw new TypeError("Super expression must either be null or a function, not " + typeof superClass); } subClass.prototype = Object.create(superClass && superClass.prototype, { constructor: { value: subClass, enumerable: false, writable: true, configurable: true } }); if (superClass) Object.setPrototypeOf ? Object.setPrototypeOf(subClass, superClass) : subClass.__proto__ = superClass; }

var ClassItem = function (_wepy$component) {
  _inherits(ClassItem, _wepy$component);

  function ClassItem() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, ClassItem);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = ClassItem.__proto__ || Object.getPrototypeOf(ClassItem)).call.apply(_ref, [this].concat(args))), _this), _this.props = {
      item: Object
    }, _this.components = {}, _this.data = {}, _this.methods = {
      goBuy: function goBuy() {
        _wepy2.default.navigateTo({
          url: '/pages/discovery/buy'
        });
      }
    }, _this.events = {}, _temp), _possibleConstructorReturn(_this, _ret);
  }

  return ClassItem;
}(_wepy2.default.component);

exports.default = ClassItem;
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImNsYXNzSXRlbS5qcyJdLCJuYW1lcyI6WyJDbGFzc0l0ZW0iLCJwcm9wcyIsIml0ZW0iLCJPYmplY3QiLCJjb21wb25lbnRzIiwiZGF0YSIsIm1ldGhvZHMiLCJnb0J1eSIsIm5hdmlnYXRlVG8iLCJ1cmwiLCJldmVudHMiLCJjb21wb25lbnQiXSwibWFwcGluZ3MiOiI7Ozs7Ozs7QUFDQTs7Ozs7Ozs7Ozs7O0lBQ3FCQSxTOzs7Ozs7Ozs7Ozs7Ozs0TEFDbkJDLEssR0FBUTtBQUNOQyxZQUFNQztBQURBLEssUUFHUkMsVSxHQUFhLEUsUUFDYkMsSSxHQUFPLEUsUUFDUEMsTyxHQUFVO0FBQ1JDLFdBRFEsbUJBQ0E7QUFDTix1QkFBS0MsVUFBTCxDQUFnQjtBQUNkQyxlQUFLO0FBRFMsU0FBaEI7QUFHRDtBQUxPLEssUUFPVkMsTSxHQUFTLEU7Ozs7RUFiNEIsZUFBS0MsUzs7a0JBQXZCWCxTIiwiZmlsZSI6ImNsYXNzSXRlbS5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIENsYXNzSXRlbSBleHRlbmRzIHdlcHkuY29tcG9uZW50IHtcbiAgcHJvcHMgPSB7XG4gICAgaXRlbTogT2JqZWN0XG4gIH1cbiAgY29tcG9uZW50cyA9IHt9O1xuICBkYXRhID0ge307XG4gIG1ldGhvZHMgPSB7XG4gICAgZ29CdXkoKSB7XG4gICAgICB3ZXB5Lm5hdmlnYXRlVG8oe1xuICAgICAgICB1cmw6ICcvcGFnZXMvZGlzY292ZXJ5L2J1eSdcbiAgICAgIH0pXG4gICAgfVxuICB9O1xuICBldmVudHMgPSB7fTtcbn1cbiJdfQ==