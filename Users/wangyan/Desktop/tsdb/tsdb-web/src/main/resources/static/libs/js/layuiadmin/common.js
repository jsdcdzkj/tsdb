layui.config({
	base: '/libs/js/layuiadmin/'
}).extend({
	index: 'lib/index',
});

layui.use(['index','layer', 'form','element','jquery','table'], function(){
	var form = layui.form,
		layer = layui.layer,
		$= layui.$,
		table = layui.table,
		element = layui.element;
	//获取当前iframe的name值
	var iframeObj = $(window.frameElement).attr('name');
	//全选
	form.on('checkbox(allChoose)', function(data) {
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
		child.each(function(index, item) {
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});
	//渲染表单
	//form.render();	
	//顶部添加方法一弹窗
	$('#addBtn1').click(function() {
		var url=$(this).attr('data-url');
		page("新增", url, iframeObj, "520", "500");
		return false;
	});
	
	//顶部添加方法二直接打开
	$('#addBtn').click(function() {
		var url=$(this).attr('data-url');
		window.location.href=url;
		return false;
	});
	
	//顶部添加方法三自适应弹窗
	$('#addBtn2').click(function() {
		var url=$(this).attr('data-url');
		page("新增", url, iframeObj, "", "");
		return false;
	});
	
	
	//顶部批量删除
	$('#delBtn').click(function() {
		var url=$(this).attr('data-url');
		var checkStatus = table.checkStatus('table-list')
        ,checkData = checkStatus.data; //得到选中的数据

        if(checkData.length === 0){
          return layer.msg('请选择数据');
        }
		
		layer.confirm('您确定要进行删除吗？', {
          btn: ['确定','取消'] //按钮
        }, function(){
          layer.msg('已删除', {icon: 1});
        }, function(){
          layer.msg('取消了');
        });
		return false;

	})

	//列表删除
	$('#table-list').on('click', '.del-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');		
		layer.confirm('您确定要进行删除吗？', {
          btn: ['确定','取消'] //按钮
        }, function(){
          layer.msg('已删除', {icon: 1});
        }, function(){
          layer.msg('取消了');
        });
		return false;
	})
	//编辑栏目方法一
	$('#table-list').on('click', '.edit-btn1', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		var urls=url+"?id="+id;
		page("编辑", urls, iframeObj, "520", "500");
		return false;
	})
	
	//编辑栏目方法二
	$('#table-list').on('click', '.edit-btn', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		window.location.href=url+"?id="+id;
		return false;
	})
	
	//编辑栏目方法三
	$('#table-list').on('click', '.edit-btn2', function() {
		var url=$(this).attr('data-url');
		var id = $(this).attr('data-id');
		var urls=url+"?id="+id;
		page("编辑", urls, iframeObj, "", "");
		return false;
	})
	
});

/**
 * 控制iframe窗口的刷新操作
 */
var iframeObjName;

//父级弹出页面
function page(title, url, obj, w, h) {
	if(title == null || title == '') {
		title = false;
	};
	if(url == null || url == '') {
		url = "404.html";
	};
	if (w == null || w == '') {
		//alert(window.innerWidth);
		w=(window.innerWidth*0.8);
	};
	 if (h == null || h == ''){
		h=(window.innerHeight - 50);
	};
	iframeObjName = obj;
	//如果手机端，全屏显示
	if(window.innerWidth <= 768) {
		var index = layer.open({
			type: 2,
			title: title,
			area: [320+'px', h +'px'],
			fixed: false, //不固定
			content: url
		});
		layer.full(index);
	} else {
		var index = layer.open({
			type: 2,
			title: title,
			maxmin: true,
			shadeClose: true, //点击遮罩关闭层
			area: [w+'px', h +'px'],
			fixed: false, //不固定
			content: url
		});
	}
}

/**
 * 刷新子页,关闭弹窗
 */
function refresh() {
	//根据传递的name值，获取子iframe窗口，执行刷新
	if(window.frames[iframeObjName]) {
		window.frames[iframeObjName].location.reload();

	} else {
		window.location.reload();
	}

	layer.closeAll();
}

// 新添加
