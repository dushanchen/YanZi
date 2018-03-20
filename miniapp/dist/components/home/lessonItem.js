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

var LessonItem = function (_wepy$component) {
  _inherits(LessonItem, _wepy$component);

  function LessonItem() {
    var _ref;

    var _temp, _this, _ret;

    _classCallCheck(this, LessonItem);

    for (var _len = arguments.length, args = Array(_len), _key = 0; _key < _len; _key++) {
      args[_key] = arguments[_key];
    }

    return _ret = (_temp = (_this = _possibleConstructorReturn(this, (_ref = LessonItem.__proto__ || Object.getPrototypeOf(LessonItem)).call.apply(_ref, [this].concat(args))), _this), _this.props = {
      item: Object
    }, _this.methods = {
      goLesson: function goLesson() {
        _wepy2.default.navigateTo({
          url: 'lessons/index?courseId=' + this.item.lessonInfo.courseId + '&lessonId=' + this.item.lessonInfo.id
        });
      }
    }, _temp), _possibleConstructorReturn(_this, _ret);
  }

  return LessonItem;
}(_wepy2.default.component);

exports.default = LessonItem;
//# sourceMappingURL=data:application/json;charset=utf-8;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImxlc3Nvbkl0ZW0uanMiXSwibmFtZXMiOlsiTGVzc29uSXRlbSIsInByb3BzIiwiaXRlbSIsIk9iamVjdCIsIm1ldGhvZHMiLCJnb0xlc3NvbiIsIm5hdmlnYXRlVG8iLCJ1cmwiLCJsZXNzb25JbmZvIiwiY291cnNlSWQiLCJpZCIsImNvbXBvbmVudCJdLCJtYXBwaW5ncyI6Ijs7Ozs7OztBQUNBOzs7Ozs7Ozs7Ozs7SUFDcUJBLFU7Ozs7Ozs7Ozs7Ozs7OzhMQUNuQkMsSyxHQUFRO0FBQ05DLFlBQU1DO0FBREEsSyxRQUdSQyxPLEdBQVU7QUFDUkMsY0FEUSxzQkFDRztBQUNULHVCQUFLQyxVQUFMLENBQWdCO0FBQ2RDLGVBQUssNEJBQTRCLEtBQUtMLElBQUwsQ0FBVU0sVUFBVixDQUFxQkMsUUFBakQsR0FBNEQsWUFBNUQsR0FBMkUsS0FBS1AsSUFBTCxDQUFVTSxVQUFWLENBQXFCRTtBQUR2RixTQUFoQjtBQUdEO0FBTE8sSzs7OztFQUo0QixlQUFLQyxTOztrQkFBeEJYLFUiLCJmaWxlIjoibGVzc29uSXRlbS5qcyIsInNvdXJjZXNDb250ZW50IjpbIlxuaW1wb3J0IHdlcHkgZnJvbSAnd2VweSdcbmV4cG9ydCBkZWZhdWx0IGNsYXNzIExlc3Nvbkl0ZW0gZXh0ZW5kcyB3ZXB5LmNvbXBvbmVudCB7XG4gIHByb3BzID0ge1xuICAgIGl0ZW06IE9iamVjdFxuICB9XG4gIG1ldGhvZHMgPSB7XG4gICAgZ29MZXNzb24oKSB7XG4gICAgICB3ZXB5Lm5hdmlnYXRlVG8oe1xuICAgICAgICB1cmw6ICdsZXNzb25zL2luZGV4P2NvdXJzZUlkPScgKyB0aGlzLml0ZW0ubGVzc29uSW5mby5jb3Vyc2VJZCArICcmbGVzc29uSWQ9JyArIHRoaXMuaXRlbS5sZXNzb25JbmZvLmlkXG4gICAgICB9KVxuICAgIH1cbiAgfTtcbn1cbiJdfQ==