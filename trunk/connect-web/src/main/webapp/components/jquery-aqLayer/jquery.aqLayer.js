/* aqLayer v2.0
   Creates a styled layer that attaches to the current element.
   Copyright (C) 2011 paul pham <http://aquaron.com/jquery/aqLayer>

   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
(function($) {
$.fn.aqLayer = function($o) {
	var __ = {
		o: $.extend({
			attach: 'ne',
			bg: '#eee',
			padding: 5,
			margin: 5
		}, $o),
		recalculate: function($ob) {
			var _y = __.o.attach.indexOf('n') >= 0 ?
				0 :
				(__.o.attach.indexOf('s') >= 0 ?
					$ob.outerHeight() :
					Math.round($ob.outerHeight() / 2));

			var _x = __.o.attach.indexOf('w') >= 0 ?
				0 :
				(__.o.attach.indexOf('e') >= 0 ?
					$ob.outerWidth() :
					Math.round($ob.outerWidth() / 2));

			_y += $ob.position().top;
			_x += $ob.position().left;

			return { top: _y, left: _x };
		}
	};

	return this.each(function() {
		var _ob = $(this), _layer = $(__.o.object);

		// we have no content
		if (!_layer.length) {
			 _layer = $('<div>No content<\/div>').appendTo('body');
		}

		_layer.css({
			display: 'none',
			position: 'absolute',
			backgroundColor: __.o.bg,
			padding: __.o.padding,
			margin: __.o.margin,
			borderRadius: 2
		})
		.addClass('aqLayer')
		.data('aqLayer', _ob)
		.keydown(function($e) {
			if ($e.keyCode === 27) {
				_layer.fadeOut();
			}
			return true;
		});

		_ob.click(function() {
			var _p = __.o.position || __.recalculate(_ob);
			_layer
				.css({ top: _p.top, left: _p.left })
				.toggle();

			if ($.isFunction(__.o.onLoad)) {
				__.o.onLoad(_layer);
			}
			return false;
		})
		.bind('close', function() { _layer.fadeOut(); });

		return true;
	});
};
})(jQuery);