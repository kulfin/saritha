function sprintf()
		{
			if (!arguments || arguments.length < 1 || !RegExp)
			{
				return;
			}
			var str = arguments[0];
			var re = /([^%]*)%('.|0|\x20)?(-)?(\d+)?(\.\d+)?(%|b|c|d|u|f|o|s|x|X)(.*)/;
			var a = b = [], numSubstitutions = 0, numMatches = 0;
			while (a = re.exec(str))
			{
				var leftpart = a[1], pPad = a[2], pJustify = a[3], pMinLength = a[4];
				var pPrecision = a[5], pType = a[6], rightPart = a[7];
				
				//alert(a + '\n' + a[0], leftpart, pPad, pJustify, pMinLength, pPrecision);

				numMatches++;
				if (pType == '%')
				{
					subst = '%';
				}
				else
				{
					numSubstitutions++;
					if (numSubstitutions >= arguments.length)
					{
						alert('Error! Not enough function arguments (' + (arguments.length - 1) + ', excluding the string)\nfor the number of substitution parameters in string (' + numSubstitutions + ' so far).');
					}
					var param = arguments[numSubstitutions];
					var pad = '';
					       if (pPad && pPad.substr(0,1) == "'") pad = leftpart.substr(1,1);
					  else if (pPad) pad = pPad;
					var justifyRight = true;
					       if (pJustify && pJustify === "-") justifyRight = false;
					var minLength = -1;
					       if (pMinLength) minLength = parseInt(pMinLength);
					var precision = -1;
					       if (pPrecision && pType == 'f') precision = parseInt(pPrecision.substring(1));
					var subst = param;
					       if (pType == 'b') subst = parseInt(param).toString(2);
					  else if (pType == 'c') subst = String.fromCharCode(parseInt(param));
					  else if (pType == 'd') subst = parseInt(param) ? parseInt(param) : 0;
					  else if (pType == 'u') subst = Math.abs(param);
					  else if (pType == 'f') {
  					  fl = parseFloat(param);
  					  if (precision > -1) {
					  	 	/*
					  	 	//alt:
					  	 	subst = Math.round(fl * Math.pow(10, precision)) / Math.pow(10, precision);					  	 	
					  	 	//if ((fl - Math.floor(fl)) == 0) {
					  	 	if ((fl - Math.floor(fl)) < (1/Math.pow(10,precision))) {
					  	 		subst = subst + ".";
					  	 		for (i=0;i<precision;i++) {
					  	 			subst = subst + "0";
					  	 		}
					  	 	}*/
					  	 	var e = Math.pow(10, precision);
                var k = (Math.round(subst * e) / e).toString();
                if (k.indexOf('.') == -1) k += '.';
                k += e.toString().substring(1);
                subst = k.substring(0, k.indexOf('.') + precision+1);
  					  } else {
					  	  subst = fl;
  					  }
					  	subst = "" + subst + "";
				  	 	//subst = subst.replace(/\./, ",");
					  }
					  else if (pType == 'o') subst = parseInt(param).toString(8);
					  else if (pType == 's') subst = param;
					  else if (pType == 'x') subst = ('' + parseInt(param).toString(16)).toLowerCase();
					  else if (pType == 'X') subst = ('' + parseInt(param).toString(16)).toUpperCase();
				}
				str = leftpart + subst + rightPart;
			}
			return str;
		}
