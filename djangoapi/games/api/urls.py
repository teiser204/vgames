from django.urls import path
from games.api.views import (
    FeatureList, FeatureDetail, 
    PlatformList, PlatformDetail,
    LanguageList, LanguageDetail,
    GenreList, GenreDetail,
    CompanyList, CompanyDetail,
    GameList, game_details
) 

urlpatterns = [

    # features
    path('features/', FeatureList.as_view(), name='api-feature-list'),
    path('features/<int:pk>/', FeatureDetail.as_view(), name='api-feature-details'),
    
    # platforms
    path('platforms/', PlatformList.as_view(), name='api-platform-list'),
    path('platforms/<int:pk>/', PlatformDetail.as_view(), name='api-platform-details'),
    
    # languages
    path('languages/', LanguageList.as_view(), name='api-language-list'),
    path('languages/<int:pk>/', LanguageDetail.as_view(), name='api-language-details'),

    # genres
    path('genres/', GenreList.as_view(), name='api-genre-list'),
    path('genres/<int:pk>/', GenreDetail.as_view(), name='api-genre-details'),

    # company
    path('companies/', CompanyList.as_view(), name='api-company-list'),
    path('companies/<int:pk>/', CompanyDetail.as_view(), name='api-company-details'),
    
    # games
    path('games/', GameList.as_view(), name='api-game-list'),
    path('games/<int:game_id>/', game_details, name='api-game-details')
]
