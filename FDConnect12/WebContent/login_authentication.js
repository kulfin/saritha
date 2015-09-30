//validate user
function validate(username,password)
{
		//alert('inside validate');
       	var myForm = document.forms[0];
       	
       	if(username.value=="" || username.length==0)
        {
        	alert("Please Enter Username");
            username.focus();
            return false;        
        }
        if(password.value=="" || password.length==0)
        {
            alert("Please Enter Password");
            password.focus();
            return false;
        }
        getValidate(username,password);
}

//Delete Brand
var getValidate = function(username,password) {
	alert('inside ')
	$.ajax( {
		type : 'POST',
		url : '../ManageBrand',
		data : {
			userAction : "validate",
			username : username,
			password : password
		},
		success : function(data) {
			var status = data.status;
			if (status == 0) {
				alert('Brand is Deleted Succesfully');
				getBrand();
			} else {
				alert('Brand Deletion Failed');
			}
		},
		error : function(error) {
			alert("Error");
		}
	});
};
