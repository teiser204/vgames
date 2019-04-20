from django.urls import path
from games.api.views import (
    FeatureList, PlatformList, LanguageList,
    GenreList, CompanyList, GameList, game_details
) 

urlpatterns = [
    path('features/', FeatureList.as_view(), name='api-feature-list'),
    path('platforms/', PlatformList.as_view(), name='api-platform-list'),
    path('languages/', LanguageList.as_view(), name='api-language-list'),
    path('genres/', GenreList.as_view(), name='api-genre-list'),
    path('companies/', CompanyList.as_view(), name='api-company-list'),
    path('games/', GameList.as_view(), name='api-game-list'),
    path('games/<int:game_id>/', game_details, name='api-game-details')
]
