<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="page" required="true" type="core.Page"%>

<ul>
	<c:if test="${ page.currentPage < 1 }">
		<li class="prev disabled"><a>&larr; Précédent</a></li>
	</c:if>
	<c:if test="${ page.currentPage >= 1 }">
		<li class="prev "><a href="Accueil?${page.backLink}">&larr;
				Précédent </a></li>
	</c:if>

	<li class="current"><a>${(page.currentPage)*page.limit + 1} à
			${(page.currentPage+1)*page.limit} sur ${page.totalCount}</a></li>
	<c:if test="${ page.currentPage >= page.nbPages }">
		<li class="next disabled"><a> Suivant &rarr; </a></li>
	</c:if>
	<c:if test="${ page.currentPage < page.nbPages }">
		<li class="next "><a href="Accueil?${page.nextLink}"> Suivant
				&rarr; </a></li>
	</c:if>
</ul>