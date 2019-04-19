from django.urls import path
from games.api.views import (
    feature_list, platform_list, language_list,
    genre_list, company_list, game_list, game_details
) 

urlpatterns = [
    path('features/', feature_list, name='api-feature-list'),
    path('platforms/', platform_list, name='api-platform-list'),
    path('languages/', language_list, name='api-language-list'),
    path('genres/', genre_list, name='api-genre-list'),
    path('companies/', company_list, name='api-company-list'),
    path('games/', game_list, name='api-game-list'),
    path('games/<int:game_id>/', game_details, name='api-game-details')
]
