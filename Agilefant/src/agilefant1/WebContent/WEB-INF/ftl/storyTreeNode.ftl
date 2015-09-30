[#ftl]
[#include "storyTreeField.ftl"]
[#assign storyTreeFieldOrder = settings.storyTreeFieldOrder.split(",")]
[#macro storyTreeNode story forceOpen=false]
	[#if story.iteration??]
		[#assign nodeType = "iteration_story"]
	[#else]
		[#assign nodeType = "story"]
	[/#if]
	<li id="storytree_${story.id?c}" storyid="${story.id?c}" storystate="${story.state}" rel="${nodeType}"[#if forceOpen] class="open"[/#if]>
		[#if story.backlog?? && story.backlog.product]
			<a class="productStory">
		[#else]
			<a>
		[/#if]
	
		<span style="display: none;">[#list story.labels as label]${label.displayName?html} [/#list]</span>
		
		[#list storyTreeFieldOrder as fieldType]
			[@storyTreeField story=story type=fieldType?trim /]
		[/#list]
		</a>

		[#if story.children?size > 0]
		<ul>
			[#list story.children as child]
				[@storyTreeNode story=child forceOpen=forceOpen /]
			[/#list]		
		</ul>
		[/#if]
	</li>
[/#macro]