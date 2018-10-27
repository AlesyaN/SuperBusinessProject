<div class="page-wrapper">
    <!-- navbar -->
    <div class="nav-wrapper fixed-top">

        <nav class="navbar navbar-light bg-light navbar-expand navbar-fixed-top">
            <div class="col-1"></div>

            <nav class="navbar navbar-light bg-light justify-content-end">
                <div class="form-inline">
                    <a href="/main" class="navbar-brand">Logo</a>
                    <a href="/search" class="btn btn-outline-success my-2 my-sm-0 <#if page="search">active</#if>">Search</a>
                </div>
            </nav>
            <div class="collapse navbar-collapse justify-content-end" id="navbarCollapse">
                <ul class="nav nav-fill nav-pills">
                    <li class="nav-item">
                        <a href="/currencies" class="nav-link <#if page="currencies">active</#if>">Currencies</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle <#if page="news" || page="analysis">active</#if>" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            Articles
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item <#if page="news">active</#if>" href="/news">News</a>
                            <a class="dropdown-item <#if page="analysis">active</#if>" href="/analysis">Analysis</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a href="/crypto" class="nav-link <#if page="crypto">active</#if>">Crypto</a>
                    </li>
                </ul>
                <div class="col-2"></div>

                <ul class="nav nav-pills justify-content-end">
                    <#if user??>
                    <li class="nav-item">
                        <a href="/profile" class="nav-link"<#if page="profile">active</#if>>Profile</a>
                    </li>
                    <#else>
                    <li class="nav-item">
                        <a href="/login" class="nav-link <#if page="login">active</#if>">Log in</a>
                    </li>
                    <li class="nav-item">
                        <a href="/sign-up" class="nav-link <#if page="sign-up">active</#if>">Sign up</a>
                    </li>
                    </#if>
                </ul>

            </div>
            <div class="col-1"></div>

        </nav>
    </div>
    <!-- end of navbar -->
</div>