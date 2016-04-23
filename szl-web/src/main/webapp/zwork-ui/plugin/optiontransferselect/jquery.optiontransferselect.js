(function ($) {
    $.fn.optiontransferselect = function (settings) {
        var defaultSettings = {
            size:10,
            listTitle: "all",
            selectedTitle: "selected"
        };
        return this.each(function () {
        	var $this = $(this);
        	
        	/* 合并默认参数和用户自定义参数 */
            settings = $.extend({},defaultSettings,{
            	size : ($this.attr("size")|| undefined),
             	width : (parseInt($this.attr("width")) || undefined),
             	listTitle : ($this.attr("listTitle")|| undefined),
             	selectedTitle : ($this.attr("selectedTitle")|| undefined),
             	selectedValues : ($this.attr("selectedValues")|| undefined),
             	fieldname : ($this.attr("name")|| undefined)
            },settings);
            
            var container = $('<div class="zwork-optiontransferselect">'
            				+ '		<div class="list">'
            				+ '			<span class="title"></span>'
            				+ '			<select></select>'
            				+ '		</div>'
            				+ '		<div class="selected">'
            				+ '			<span class="title"></span>'
            				+ '			<select></select>'
            				+ '		</div>'
            				+ '		<div class="hiddenFileds">'
            				+ '		</div>'
            		 		+ '</div>'),
            	list = $(".list>select", container),
                selected = $(".selected>select", container),
                hiddenFields = $(".hiddenFileds", container);
            
            //设置标题
			if (settings.listTitle && settings.listTitle != '') {
				$(".list>span", container).html(settings.listTitle);
			}
            if (settings.selectedTitle && settings.selectedTitle != ''){
            	$(".selected>span", container).html(settings.selectedTitle);
            }
            
            //处理已选中的选项
            if (settings.selectedValues) {
                selectedValues = settings.selectedValues.split(",");
                for (var i = 0; i < selectedValues.length; i++) {
                	var option = $this.find("option[value='" + selectedValues[i] + "']");
					if (option.length > 0) {
						var hiddenObj = $('<input type="checkbox" checked="checked" name="' + settings.fieldname + '" value="' + selectedValues[i] + '" />');
						hiddenFields.append(hiddenObj);
						
						option.remove();
						selected.append(option);
					}
                }
            }
            
            list.html($this.html());
            $this.replaceWith(container);
            
			// 设置select显示的选项个数
			list.attr('size', settings.size);
			selected.attr('size', settings.size);
			
			// 设置宽度
			if (settings.width) {
				$(".list", container).width(settings.width);
				$(".selected", container).width(settings.width);
			}
			container.width($(".list", container).outerWidth(true) + $(".selected", container).outerWidth(true));
			container.height($(".list", container).outerHeight(true));
			
            // 解决firefox5下两个select如果一个有选项，另一个无选项时高度不一致的bug
			if ($.browser.mozilla) {
				var maxHeight = Math.max(list.outerHeight(true),selected.outerHeight(true));
				list.height(maxHeight);
				selected.height(maxHeight);
			}
			
            list.bind("click", function () {
				var option = $(this).find("option[value='" + $(this).val() + "']");
				if(option.length > 0){
					var hiddenObj = $('<input type="checkbox" checked="checked" name="' + settings.fieldname + '" value="' + $(this).val() + '" />');
					selected.append(option);
					hiddenFields.append(hiddenObj);
	
					selected.val('');
					list.val('');
				}
            });
            
            selected.bind("click", function () {
				var option = $(this).find("option[value='" + $(this).val() + "']");
				if(option.length > 0){
					var hiddenObj = $("input[type='checkbox'][value='" + $(this).val() + "']",container);
					list.append(option);
					hiddenObj.remove();
	
					selected.val('');
					list.val('');
				}
            });
        });
    };
})(jQuery);