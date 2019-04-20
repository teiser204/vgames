from django_filters import rest_framework as filters
from games.models import Game

# --------------------------------------------------------------------
# Game list filters
# --------------------------------------------------------------------
class GameFilter(filters.FilterSet):
    title       = filters.CharFilter(field_name='title', lookup_expr='icontains')
    desc        = filters.CharFilter(field_name='desc', lookup_expr='icontains')
    from_year   = filters.NumberFilter(field_name='year', lookup_expr='gte')
    to_year     = filters.NumberFilter(field_name='year', lookup_expr='lte')
    min_price   = filters.NumberFilter(field_name='price', lookup_expr='gte')
    max_price   = filters.NumberFilter(field_name='price', lookup_expr='lte')
    company     = filters.NumberFilter(field_name='company', lookup_expr='exact')
    genre       = filters.NumberFilter(field_name='genres__id', lookup_expr='exact')
    feature     = filters.NumberFilter(field_name='features__id', lookup_expr='exact')
    platform    = filters.NumberFilter(field_name='platforms__id', lookup_expr='exact')
    language    = filters.NumberFilter(field_name='languages__id', lookup_expr='exact')
    from_rating = filters.NumberFilter(field_name='rating', lookup_expr='gte');

    
    class Meta:
        model = Game
        fields = [
            'title',
            'desc',
            'min_price',
            'max_price',
            'from_year',
            'to_year',
            'company',
            'genre',
            'feature',
            'platform',
            'language',
            'from_rating',
        ]


