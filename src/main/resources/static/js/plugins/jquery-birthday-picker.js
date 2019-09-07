$(function ($)
{
    var month = {
        "number": ["01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"],
        "short": ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        "long": ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
    },
    today = new Date(), 
    todayYear = today.getFullYear(),
    todayMonth = today.getMonth() + 1,
    todayDay = today.getDate();

    generateBirthdayPicker = function ($parent, options) 
    {
        if($parent.attr('id') == "datepicker") {
            var $year = $parent.find("#year"),
                $day = $parent.find("#day"),
                $month = $parent.find("#month");
        } else {
            var $year = $parent.find("#e-year"),
                $day = $parent.find("#e-day"),
                $month = $parent.find("#e-month");
        }

         //calculate the year to add to the select options. 
         var yearBegin = todayYear - options.minAge; 
         var yearEnd = todayYear - options.maxAge;
         if (options.maxYear != todayYear && options.maxYear > todayYear) {
             yearBegin = options.maxYear; 
             yearEnd = yearEnd + (options.maxYear - todayYear)
         }
         for (var i = yearBegin; i >= yearEnd; i--) {              
             $("<option></option>").attr("value", i).text(i).appendTo($year); 
         }       
        
         for (var i = 0; i <= 11; i++) {
             $("<option></option>").attr('value', i + 1).text(month[options.monthFormat][i]).appendTo($month);
         }
         for (var i = 1; i <= 31; i++) {
             var number = (i < 10) ? "0"+i: i;
             $("<option></option>").attr('value', i).text(number).appendTo($day);
         }
    }

    $.fn.birthdayPicker = function(options) 
    {
        return this.each(function () {
            var settings = $.extend($.fn.birthdayPicker.defaults, options );
            generateBirthdayPicker($(this), settings);
        });
    };

    $.fn.birthdayPicker.defaults = {
        "maxAge"        : 100,
          "minAge"        : 0,
          "maxYear"       : todayYear,
          "dateFormat"    : "middleEndian",
          "monthFormat"   : "number",
          "placeholder"   : true,
          "defaultDate"   : false,
          "sizeClass"        : "span2",
        'callback': false
    }
}( jQuery ))

