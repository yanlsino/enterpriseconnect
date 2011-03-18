// ----------------------------------------------------------------------------
// markItUp!
// ----------------------------------------------------------------------------
// Copyright (C) 2008 Jay Salvat
// http://markitup.jaysalvat.com/
// ----------------------------------------------------------------------------
myVBlogSettings = {
    nameSpace:          "vblog", // Useful to prevent multi-instances CSS conflict
    //previewParserPath:  "~/sets/wiki/preview.php",
    onShiftEnter:       {keepDefault:false, replaceWith:'\n\n'},
    markupSet:  [
			{name:'Bold', key:'B', openWith:"'''", closeWith:"'''"}, 
			{name:'Italic', key:'I', openWith:"''", closeWith:"''"}, 
			{name:'Stroke through', key:'S', openWith:'<s>', closeWith:'</s>'},
			//{separator:'---------------' },
            {name:'Url', openWith:'[[![URL地址:!:http://]!] ', closeWith:']', placeHolder:'超链显示名...' }
    ]
}
