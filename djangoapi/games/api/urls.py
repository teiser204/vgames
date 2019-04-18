from django.urls import path
from games.api.views import (
    feature_list, platform_list, language_list,
    genre_list, company_list
) 

urlpatterns = [
    path('features/', feature_list, name='api-features'),
    path('platforms/', platform_list, name='api-platforms'),
    path('languages/', language_list, name='api-languages'),
    path('genres/', genre_list, name='api-genres'),
    path('companies/', company_list, name='api-companies'),
]
