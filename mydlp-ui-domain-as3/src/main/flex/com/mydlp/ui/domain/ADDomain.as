/**
 * Generated by Gas3 v2.0.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package com.mydlp.ui.domain {
	import flash.utils.getQualifiedClassName;
	
	import mx.collections.ArrayCollection;
	import mx.collections.ListCollectionView;

    [Managed]
    [RemoteClass(alias="com.mydlp.ui.domain.ADDomain")]
    public class ADDomain extends ADDomainBase {
		
		[Embed("../../../../../../../../mydlp-ui-flex/src/main/flex/assets/icons/16x16/notebook.png")]
		public static const ICON_CLASS:Class;
		
		public function get icon(): Object
		{
			return ICON_CLASS;
		}
		
		public function get children(): ListCollectionView
		{
			if (this.root == null)
				return null;
			else
			{
				for each (var i:* in this.root.children)  // Workaround for type loss
					if (i is ADDomainUser)
						i as ADDomainUser;
					else if (i is ADDomainOU)
						i as ADDomainOU;
					
				return this.root.children;
			}
		}

		public function get label(): String
		{
			var str:String = this.domainName;
			
			var dnLength:Number = ( "dc=" + this.domainName.split(".").join(",dc=") ).length;
			if (this.baseDistinguishedName.length > (dnLength + 1))
			{
				str = str + " [" + this.baseDistinguishedName.substr(0, (this.baseDistinguishedName.length - 1 - dnLength)) + "]";
			}
			return str;
		}

    }
}